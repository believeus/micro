package cn.believeus.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Ttask;
import cn.believeus.model.TuserEvent;
import cn.believeus.service.MySQLService;


@Controller
public class TaskController {
	
	@Resource
	private MySQLService service;
	
	@RequestMapping("/admin/task/list")
	public ModelAndView list() {
		ModelAndView modelView=new ModelAndView();
		List<?> taskList=service.findObjectList(Ttask.class, 10);
		modelView.addObject("tasklist", taskList);
		modelView.setViewName("/WEB-INF/back/task/list.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/task/review")
	public ModelAndView review() {
		ModelAndView modelView=new ModelAndView();
		List<?> userEventList=service.findObjectList(TuserEvent.class, 10);
		modelView.addObject("userEventList", userEventList);
		modelView.setViewName("/WEB-INF/back/task/review.jsp");
		return modelView;
	}
}
