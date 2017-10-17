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
	
	@RequestMapping("/admin/stu/update")
	public @ResponseBody String update(Tuser user){
		service.saveOrUpdate(user);
		return "true";
	}
	
	@RequestMapping("/admin/stu/del")
	public @ResponseBody String del(int id){
		service.delete(Tuser.class, id);
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
		modelView.addObject("eventList",eventList);
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
		int learnValue=event.getLearnValue();
		int liveValue=event.getLiveValue();
		switch (learnValue) {
			case 0:
				userEvent.setLiveValue(liveValue);
				break;
			default:
				userEvent.setLearnValue(learnValue);
				break;
		}
		//审核状态
		userEvent.setStatus("事件发生");
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		userEvent.setTruename(user.getTruename());
		service.saveOrUpdate(userEvent);
		return "true";
	}
	@RequestMapping("/admin/stu/delBindEvent")
	public @ResponseBody String delBindEvent(int userEventId){
		TuserEvent userEvent = (TuserEvent)service.findObject(TuserEvent.class, userEventId);
		service.delete(userEvent);
		return "true";
	}
	/**申请驳回页面*/
	@RequestMapping("/admin/stu/disagreeView")
	public ModelAndView disagreeView(int userEventId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("userEventId",userEventId);
		modelView.setViewName("/WEB-INF/back/stu/unAgree.jsp");
		return modelView;
	}
	
	/**不同意*/
	@RequestMapping("/admin/stu/disagree")
	public @ResponseBody String disagree(int userEventId,String message){
		TuserEvent userEvent = (TuserEvent)service.findObject(TuserEvent.class, userEventId);
		userEvent.setMessage(message);
		userEvent.setStatus("管理员审核中");
		service.saveOrUpdate(userEvent);
		return "true";
	}
	
	@RequestMapping("/admin/stu/uppasswd")
	public @ResponseBody String uppasswd(Integer userId,String password){
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		user.setPassword(password);
		service.saveOrUpdate(user);
		return "true";
	}
	
	@RequestMapping("/admin/stu/uppasswdView")
	public ModelAndView uppasswdView(Integer userId){
		ModelAndView modelview=new ModelAndView();
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		modelview.setViewName("/WEB-INF/back/stu/uppassswd.jsp");
		modelview.addObject("user", user);
		return modelview;
	}
}
