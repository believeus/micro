package cn.believeus.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Tevent;
import cn.believeus.service.MySQLService;

@Controller
public class EventController {
	@Resource
	private MySQLService service;
	
	@RequestMapping("/admin/event/list")
	public ModelAndView listView(){
		ModelAndView modelView=new ModelAndView();
		List<?> eventList = service.findObjectList(Tevent.class, 15);
		modelView.setViewName("/WEB-INF/back/event/list.jsp");
		modelView.addObject("eventList", eventList);
		return modelView;
	}
	
	@RequestMapping("/admin/event/addView")
	public String addView(){
		return "/WEB-INF/back/event/add.jsp";
	}
	
	@RequestMapping("/admin/event/editView")
	public ModelAndView editView(int id){
		ModelAndView modelView=new ModelAndView();
		Tevent event = (Tevent)service.findObject(Tevent.class, id);
		modelView.setViewName("/WEB-INF/back/event/edit.jsp");
		modelView.addObject("event",event);
		return modelView;
	}
	@RequestMapping("/admin/event/update")
	public @ResponseBody String update(Tevent event){
		service.saveOrUpdate(event);
		return "true";
	}
	
	@RequestMapping("/admin/event/del")
	public @ResponseBody String del(int id){
		service.delete(Tevent.class, id);
		return "true";
	}
}
