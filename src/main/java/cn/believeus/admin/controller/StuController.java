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
import cn.believeus.model.Treject;
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
		modelView.addObject("userEventList", userEventList);
		modelView.addObject("userId", userId);
		return modelView;
	}
	
	@RequestMapping("/admin/stu/eventView")
	public ModelAndView eventView(int userId){
		ModelAndView modelView=new ModelAndView();
		List<?> eventList = service.findObjectList(Tevent.class, 8);
		modelView.setViewName("/WEB-INF/back/stu/bindevent.jsp");
		modelView.addObject("vList",eventList);
		modelView.addObject("userId", userId);
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
		//审核状态
		userEvent.setStatus("申请仲裁");
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		int value=user.getValue()+Integer.parseInt(event.getValue());
		user.setValue(value);
		service.saveOrUpdate(user);
		service.saveOrUpdate(userEvent);
		return "true";
	}
	@RequestMapping("/admin/stu/delBindEvent")
	public @ResponseBody String delBindEvent(int userId,int eventId){
		TuserEvent userEvent = (TuserEvent)service.findObject(TuserEvent.class, eventId);
		int value =Integer.parseInt(userEvent.getValue());
		Tuser user=(Tuser)service.findObject(Tuser.class, userId);
		user.setValue(user.getValue()-value);
		service.saveOrUpdate(user);
		service.delete(userEvent);
		return "true";
	}
	/**申请驳回页面*/
	@RequestMapping("/admin/stu/disagreeView")
	public ModelAndView disagreeView(int userId,int eventId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("userId",userId);
		modelView.addObject("eventId",eventId);
		modelView.setViewName("/WEB-INF/back/stu/unAgree.jsp");
		return modelView;
	}
	
	/**不同意*/
	@RequestMapping("/admin/stu/disagree")
	public @ResponseBody String disagree(Treject reject){
		ModelAndView modelView=new ModelAndView();
		int eventId = reject.getEventId();
		int userId = reject.getUserId();
		TuserEvent userEvent =(TuserEvent)service.findObject(TuserEvent.class, eventId);
		Tuser user =(Tuser)service.findObject(Tuser.class, userId);
		reject.setTitle(userEvent.getTitle());
		reject.setUsername(user.getUsername());
		userEvent.setStatus("管理员审核中");
		service.saveOrUpdate(userEvent);
		service.saveOrUpdate(reject);
		modelView.addObject("reject", reject);
		return "true";
	}
}
