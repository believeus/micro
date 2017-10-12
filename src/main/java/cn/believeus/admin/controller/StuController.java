package cn.believeus.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Tevent;
import cn.believeus.model.Tuser;
import cn.believeus.model.TuserEvent;
import cn.believeus.service.MySQLService;

@Controller
public class StuController {
	
	@Resource
	private MySQLService service;
	
	@RequestMapping("/admin/stu/list")
	public ModelAndView list(){
		ModelAndView modelView=new ModelAndView();
		List<?> userlist = service.findObjectList(Tuser.class, 15);
		modelView.addObject("userlist", userlist);
		modelView.setViewName("/WEB-INF/back/stu/list.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/stu/editView")
	public ModelAndView editView(int id){
		ModelAndView modelView=new ModelAndView();
		Tuser user =(Tuser)service.findObject(Tuser.class, id);
		modelView.addObject("user", user);
		modelView.setViewName("/WEB-INF/back/stu/edit.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/stu/addView")
	public String addView(){
		return "/WEB-INF/back/stu/add.jsp";
	}
	
	@RequestMapping("/admin/stu/saveOrUpdata")
	public @ResponseBody String saveOrUpdata(Tuser user){
		service.saveOrUpdate(user);
		return "true";
	}
	
	@RequestMapping("/admin/stu/myDo")
	public ModelAndView myDo(int userId){
		ModelAndView modelView=new ModelAndView();
		List<?> userEventList=service.findObjectList(TuserEvent.class, "userId", userId,15);
		modelView.setViewName("/WEB-INF/back/stu/eventlist.jsp");
		modelView.addObject("vList", userEventList);
		return modelView;
	}
	
	@RequestMapping("/admin/stu/eventView")
	public ModelAndView eventView(int userId){
		ModelAndView modelView=new ModelAndView();
		List<?> eventList = service.findObjectList(Tevent.class, 8);
		modelView.setViewName("/WEB-INF/back/stu/bindevent.jsp");
		modelView.addObject("vList",eventList);
		return modelView;
	}
	
	@RequestMapping("/admin/stu/bindEvent")
	public @ResponseBody String bindEvent(String username,int userId,int eventId){
		Tevent event =(Tevent)service.findObject(Tevent.class, eventId);
		TuserEvent userEvent=new TuserEvent();
		userEvent.setEventId(eventId);
		userEvent.setUserId(userId);
		userEvent.setTitle(event.getTitle());
		userEvent.setType(event.getType());
		userEvent.setObserver(username);
		userEvent.setValue(event.getValue());
		service.saveOrUpdate(userEvent);
		return "true";
	}
}
