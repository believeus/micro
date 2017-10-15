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
		ModelAndView modelView=new ModelAndView();
		List<?> taskList=service.findObjectList(Ttask.class, 10);
		modelView.addObject("tasklist", taskList);
		modelView.setViewName("/WEB-INF/back/task/listview.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/task/addview")
	public ModelAndView addview() {
		ModelAndView modelView=new ModelAndView();
		modelView.setViewName("/WEB-INF/back/task/addview.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/task/buildtask")
	public@ResponseBody String buildtask(Ttask task,HttpSession session){
		Tuser user = (Tuser)session.getAttribute(Variables.SESSION_USER);
		task.setUser(user);
		service.saveOrUpdate(task);
		return "true";
	}
	
	@ModelAttribute("task")
	public void populateModel(@RequestParam(required=false) Integer id, Model model,HttpServletRequest req){
		String uri = req.getRequestURI().toString();
		if(uri.contains("admin/task/update")||uri.contains("admin/task/upstatus")){
			Ttask task = (Ttask)service.findObject(Ttask.class, id);
			model.addAttribute("task",task);
		}
	}
	
	@RequestMapping("/admin/task/update")
	public @ResponseBody String update(@ModelAttribute("task")Ttask task){
		service.saveOrUpdate(task);
		return "true";
	}
	
	@RequestMapping("/admin/task/upstatus")
	public @ResponseBody String updatestatus(@ModelAttribute("task")Ttask task){
		//给救助者减分,如果是管理员则无需减分
		if(!task.getUser().getRole().getRoleName().equals("root")){
			int userId = task.getUser().getId();
			Tuser user = (Tuser)service.findObject(Tuser.class, userId);
			int value=user.getValue()-Integer.parseInt(task.getValue());
			user.setValue(value);
			service.saveOrUpdate(user);
		}
		
		//给帮助着加分
		int aidUserId = task.getAidUser().getId();
		Tuser aiduser = (Tuser)service.findObject(Tuser.class, aidUserId);
		int value=aiduser.getValue()+Integer.parseInt(task.getValue());
		aiduser.setValue(value);
		service.saveOrUpdate(aiduser);
		//将任务放入用户事件中
		TuserEvent userEvent=new TuserEvent();
		userEvent.setTitle(task.getTitle());
		userEvent.setUserId(aidUserId);
		userEvent.setUsername(aiduser.getUsername());
		userEvent.setObserver(task.getUser().getUsername());
		userEvent.setType("加分项:领取任务大厅任务");
		userEvent.setValue(task.getValue());
		userEvent.setStatus(task.getStatus());
		service.saveOrUpdate(userEvent);
		service.saveOrUpdate(task);
		return "true";
		
	}
	
	@RequestMapping("/admin/task/taskstatus")
	public ModelAndView taskstatus(int taskId){
		 ModelAndView modelView=new ModelAndView();
		 Ttask task = (Ttask)service.findObject(Ttask.class, taskId);
		 String view="/WEB-INF/back/task/statusview.jsp";
		 modelView.addObject("task",task);
		 modelView.setViewName(view);
		 return modelView;
	}
	
	@RequestMapping("/admin/task/del")
	public @ResponseBody String del(Integer taskId){
		service.delete(Ttask.class, taskId);
		return "true";
	}
	
	@RequestMapping("/admin/task/editview")
	public ModelAndView editView(int taskId){
		ModelAndView modelView=new ModelAndView();
		Ttask task=(Ttask)service.findObject(Ttask.class, taskId);
		modelView.addObject("task", task);
		modelView.setViewName("/WEB-INF/back/task/editview.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/task/iCanDoIt")
	public @ResponseBody String iCanDoIt(int taskId,int aidUserId){
		Ttask task = (Ttask)service.findObject(Ttask.class, taskId);
		Tuser aidUser = (Tuser)service.findObject(Tuser.class, aidUserId);
		task.setAidUser(aidUser);
		task.setStatus("任务已被认领");
		service.saveOrUpdate(task);
		return "true";
	}
}
