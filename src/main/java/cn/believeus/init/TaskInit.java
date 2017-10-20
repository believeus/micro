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
						String hql="from Tuser where status  in ('考核期','缓冲期') or (status='学习期' and learnValue < 700) ";
						List<Tuser> userList = (List<Tuser>) service.findObjectList(hql);
						Tuser user = (Tuser) service.findObject(Tuser.class, 1);
						// 考核期的学员,一天要问三个问题
						for (Tuser aidUser : userList) {
							Ttask task = new Ttask(user, "betting-learn", 2,aidUser,"任务已被指派");
							task.setBegintime(begintime);
							task.setEndtime(endtime);
							service.merge(task);

						}
					}
				}, 1000, 60000000);
			}

		}
	}
	
}
