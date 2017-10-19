package cn.believeus.init;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import cn.believeus.model.Ttask;
import cn.believeus.model.Tuser;
import cn.believeus.service.MySQLService;

@Component
public class TaskInit implements ApplicationListener<ApplicationEvent> {
	@Resource
	private MySQLService service;
	Timer timer = null;

	@Override
	@SuppressWarnings("unchecked")
	public void onApplicationEvent(ApplicationEvent event) {
		if (event instanceof ContextRefreshedEvent) {
			if (timer == null) {
				timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						String begintime=new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(new Date());
						String endtime=new SimpleDateFormat("yyyy-MM-dd 11:00:00").format(new Date());
						List<Tuser> userList = (List<Tuser>) service.findObjectList(Tuser.class, "status", "考核期");
						Tuser user = (Tuser) service.findObject(Tuser.class, 1);
						// 考核期的学员,一天要问三个问题
						for (Tuser aidUser : userList) {
							for (int i = 0; i < 3; i++) {
								Ttask task = new Ttask(user, "betting", 2,aidUser,"任务已被指派");
								task.setBegintime(begintime);
								task.setEndtime(endtime);
								service.merge(task);
							}

						}
						// 缓冲期的学员一天要问2个问题
						userList = (List<Tuser>) service.findObjectList(Tuser.class, "status", "缓冲期");
						for (Tuser aidUser : userList) {
							for (int i = 0; i < 2; i++) {
								Ttask task = new Ttask(user, "betting", 2,aidUser,"任务已被指派");
								task.setBegintime(begintime);
								task.setEndtime(endtime);
								service.merge(task);
							}

						}
						// 在学习期,并且分数小于500分的的需要问3个问题
						String hql = "from Tuser u where u.status='学习期' and u.learnValue < 500";
						userList = (List<Tuser>) service.findObjectList(hql);
						for (Tuser aidUser : userList) {
							for (int i = 0; i < 3; i++) {
								Ttask task = new Ttask(user, "betting", 2,aidUser,"任务已被指派");
								task.setBegintime(begintime);
								task.setEndtime(endtime);
								service.merge(task);
							}
						}
						// 在学习期,并且分数在500到700之间的需要问1个问题
						hql = "from Tuser u where u.status='学习期' and u.learnValue >= 500 and u.learnValue <=700";
						userList = (List<Tuser>) service.findObjectList(hql);
						for (Tuser aidUser : userList) {
							Ttask task = new Ttask(user, "betting", 2,aidUser,"任务已被指派");
							task.setBegintime(begintime);
							task.setEndtime(endtime);
							service.merge(task);
						}
					}
				}, 2000, 60000000);
			}

		}
	}
	
}
