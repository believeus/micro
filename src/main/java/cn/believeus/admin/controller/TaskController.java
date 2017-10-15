package cn.believeus.admin.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Ttask;
import cn.believeus.model.Tuser;
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
	
	@RequestMapping("/admin/task/update")
	public@ResponseBody String update(Ttask task,HttpSession session){
		Tuser user = (Tuser)session.getAttribute(Variables.SESSION_USER);
		task.setStatus("求帮助");
		task.setUser(user);
		service.saveOrUpdate(task);
		return "true";
	}
	@RequestMapping("/admin/task/del")
	public @ResponseBody String del(Integer taskId){
		service.delete(Ttask.class, taskId);
		return "true";
	}
	
	/*public @ResponseBody String iCanDoIt(int taskId,int curUserId){
		Ttask task = (Ttask)service.findObject(Ttask.class, taskId);
		Tuser user = (Tuser)service.findObject(Tuser.class, curUserId);
	}*/
}
