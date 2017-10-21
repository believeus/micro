package cn.believeus.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Trole;
import cn.believeus.model.Ttask;
import cn.believeus.model.Tuser;
import cn.believeus.model.TuserEvent;
import cn.believeus.service.MySQLService;
import cn.believeus.variables.Variables;

@Controller
public class TaskController {

	@Resource
	private MySQLService service;

	@RequestMapping("/admin/task/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView();
		List<?> taskList = service.findObjectList(Ttask.class, 10);
		modelView.addObject("tasklist", taskList);
		modelView.setViewName("/WEB-INF/back/task/listview.jsp");
		return modelView;
	}

	@RequestMapping("/admin/task/addview")
	public ModelAndView addview() {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("/WEB-INF/back/task/addview.jsp");
		return modelView;
	}

	@RequestMapping("/admin/task/buildtask")
	public @ResponseBody
	String buildtask(Ttask task, HttpSession session) {
		Tuser user = (Tuser) session.getAttribute(Variables.SESSION_USER);
		task.setUser(user);
		service.saveOrUpdate(task);
		return "true";
	}

	@ModelAttribute("task")
	public void populateModel(@RequestParam(required = false) Integer id,Model model, HttpServletRequest req) {
		String uri = req.getRequestURI().toString();
		if (uri.contains("admin/task/update")|| uri.contains("admin/task/upstatus")) {
			Ttask task = (Ttask) service.findObject(Ttask.class, id);
			model.addAttribute("task", task);
		}
	}

	@RequestMapping("/admin/task/update")
	public @ResponseBody
	String update(@ModelAttribute("task") Ttask task) {
		service.saveOrUpdate(task);
		return "true";
	}

	@RequestMapping("/admin/task/upstatus")
	public @ResponseBody
	String updatestatus(@ModelAttribute("task") Ttask task) {
		// 获得任务发布者
		Tuser user = task.getUser();
		Trole role = user.getRole();
		Tuser aidUser = task.getAidUser();
		TuserEvent userEvent = new TuserEvent();
		Integer value = task.getValue();
		String type = task.getType();
		if(type.equals("reward-live")||type.equals("assign-live")){
			type="live";
			if(task.getStatus().equals("该任务未完成")){
				aidUser.setLiveValue(aidUser.getLiveValue() - value);
				userEvent.setValue((-value));
			//酌情给分和任务完成
			}else{
				//如果是管理员发布的任务,管理员不减分,并且给接受任务的人加分
				if(!role.getRoleName().equals("admin")){
					user.setLiveValue(user.getLiveValue() - value);
				}
				aidUser.setLiveValue(aidUser.getLiveValue() + value);
				userEvent.setValue(value);
			}
		//添加学习分
		}else if(type.equals("reward-learn")||type.equals("assign-learn")) {
			type="learn";
			if(task.getStatus().equals("该任务未完成")){
				aidUser.setLearnValue(aidUser.getLearnValue() - value);
				userEvent.setValue((-value));
			//酌情给分和任务完成
			}else {
				//如果是管理员发布的任务,管理员不减分
				if(!role.getRoleName().equals("admin")){
					user.setLearnValue(user.getLearnValue() - value);
				}
				aidUser.setLearnValue(aidUser.getLearnValue() + value);
				userEvent.setValue(value);
			}
		//对赌积分,完成任务加分,完成不了任务扣分
		}else if(type.equals("betting-learn")){
			//学习任务完成,转换成学习分
			type="learn";
			if(task.getStatus().equals("该任务未完成")){
				aidUser.setLearnValue(aidUser.getLearnValue() - value);
				userEvent.setValue((-value));
			//酌情给分和任务完成
			}else{
				//如果是管理员发布的任务,管理员不减分
				if(!role.getRoleName().equals("admin")){
					user.setLearnValue(user.getLearnValue() - value);
				}
				aidUser.setLearnValue(aidUser.getLearnValue() + value);
				userEvent.setValue(value);
			}
		}else if(type.equals("betting-live")){
			//学习任务完成,转换成学习分
			type="live";
			if(task.getStatus().equals("该任务未完成")){
				aidUser.setLiveValue(aidUser.getLiveValue()- value);
				userEvent.setValue((-value));
			}else if(task.getStatus().equals("任务已经完成")){
				//如果是管理员发布的任务,管理员不减分
				if(!role.getRoleName().equals("admin")){
					user.setLearnValue(user.getLiveValue() - value);
				}
				aidUser.setLearnValue(aidUser.getLiveValue() + value);
				userEvent.setValue(value);
			}
		}
		
		// 如果不是管理员则给救助者减分
		if(!role.getRoleName().equals("admin")){
			service.saveOrUpdate(user);
		}
		service.saveOrUpdate(aidUser);
		// 将任务放入用户事件中
		userEvent.setTitle(task.getTitle());
		userEvent.setUserId(aidUser.getId());
		userEvent.setTruename(aidUser.getTruename());
		userEvent.setObserver(task.getUser().getUsername());
		userEvent.setType(type);
		userEvent.setStatus(task.getStatus());
		service.saveOrUpdate(userEvent);
		service.saveOrUpdate(task);
		return "true";

	}

	@RequestMapping("/admin/task/taskstatus")
	public ModelAndView taskstatus(int taskId) {
		ModelAndView modelView = new ModelAndView();
		Ttask task = (Ttask) service.findObject(Ttask.class, taskId);
		String view = "/WEB-INF/back/task/statusview.jsp";
		modelView.addObject("task", task);
		modelView.setViewName(view);
		return modelView;
	}

	@RequestMapping("/admin/task/del")
	public @ResponseBody
	String del(Integer taskId) {
		service.delete(Ttask.class, taskId);
		return "true";
	}

	@RequestMapping("/admin/task/editview")
	public ModelAndView editView(int taskId) {
		ModelAndView modelView = new ModelAndView();
		Ttask task = (Ttask) service.findObject(Ttask.class, taskId);
		modelView.addObject("task", task);
		modelView.setViewName("/WEB-INF/back/task/editview.jsp");
		return modelView;
	}

	@RequestMapping("/admin/task/iCanDoIt")
	public @ResponseBody
	String iCanDoIt(int taskId, int aidUserId) {
		Ttask task = (Ttask) service.findObject(Ttask.class, taskId);
		Tuser aidUser = (Tuser) service.findObject(Tuser.class, aidUserId);
		task.setAidUser(aidUser);
		task.setStatus("任务已被认领");
		service.saveOrUpdate(task);
		return "true";
	}
	
	@RequestMapping("/admin/task/editQ")
	public ModelAndView editQView(String taskId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("taskId", taskId);
		modelView.setViewName("/WEB-INF/back/task/taskQ.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/task/upQ")
	public @ResponseBody String upQ(int taskId,String title){
		Ttask task= (Ttask)service.findObject(Ttask.class, taskId);
		task.setTitle(title);
		service.saveOrUpdate(task);
		return "true";
	}
	
}
