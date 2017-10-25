package cn.believeus.admin.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import cn.believeus.PaginationUtil.Page;
import cn.believeus.PaginationUtil.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Tevent;
import cn.believeus.model.Tuser;
import cn.believeus.model.TuserEvent;
import cn.believeus.service.MySQLService;

@Controller
public class StudnController {
	
	@Resource
	private MySQLService service;
	
	@RequestMapping("/admin/studn/list")
	public ModelAndView list(Pageable pageable){
		ModelAndView modelView=new ModelAndView();
		Page<?> userlist = service.findObjectPage(Tuser.class, pageable);
		modelView.addObject("userlist", userlist);
		modelView.setViewName("/WEB-INF/back/studn/list.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/studn/editView")
	public ModelAndView editView(int id){
		ModelAndView modelView=new ModelAndView();
		Tuser user =(Tuser)service.findObject(Tuser.class, id);
		modelView.addObject("user", user);
		modelView.setViewName("/WEB-INF/back/studn/edit.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/studn/addView")
	public String addView(){
		return "/WEB-INF/back/studn/add.jsp";
	}
	
	@RequestMapping("/admin/studn/update")
	public @ResponseBody String update(Tuser user){
		service.saveOrUpdate(user);
		byte b = 1;
		System.out.println(b==1);
		return "true";
	}
	
	@RequestMapping("/admin/studn/del")
	public @ResponseBody String del(int id){
		service.delete(Tuser.class, id);
		return "true";
	}
	
	@RequestMapping("/admin/studn/doneView")
	public ModelAndView doneView(int userId,String type,Pageable pageable){
		ModelAndView modelView=new ModelAndView();
		String hql="from TuserEvent e where e.userId="+userId+" and type='"+type+"'";
		Page<?> userEventList = service.findObjectPage(TuserEvent.class, pageable);
		modelView.setViewName("/WEB-INF/back/studn/eventlist.jsp");
		modelView.addObject("userEventList", userEventList);
		modelView.addObject("userId", userId);
		return modelView;
	}
	
	@RequestMapping("/admin/studn/eventView")
	public ModelAndView eventView(int userId,Pageable pageable){
		ModelAndView modelView=new ModelAndView();
		Page<?> eventList = service.findObjectPage(Tevent.class, pageable);
		modelView.setViewName("/WEB-INF/back/studn/bindevent.jsp");
		modelView.addObject("eventList",eventList);
		modelView.addObject("userId", userId);
		return modelView;
	}
	
	@RequestMapping("/admin/studn/bindEvent")
	public @ResponseBody String bindEvent(String username,int userId,int eventId){
		Tevent event =(Tevent)service.findObject(Tevent.class, eventId);
		TuserEvent userEvent=new TuserEvent();
		userEvent.setEventId(eventId);
		userEvent.setUserId(userId);
		userEvent.setTitle(event.getTitle());
		userEvent.setType(event.getType());
		userEvent.setObserver(username);
		int value=event.getValue();
		String type = userEvent.getType();
		if(type.equals("live")){
			userEvent.setValue(value);
		}else {
			userEvent.setValue(value);
		}
		//审核状态
		userEvent.setStatus("事件发生");
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		userEvent.setTruename(user.getTruename());
		service.saveOrUpdate(userEvent);
		return "true";
	}
	@RequestMapping("/admin/studn/delBindEvent")
	public @ResponseBody String delBindEvent(int userEventId){
		TuserEvent userEvent = (TuserEvent)service.findObject(TuserEvent.class, userEventId);
		service.delete(userEvent);
		return "true";
	}
	/**申请驳回页面*/
	@RequestMapping("/admin/studn/disagreeView")
	public ModelAndView disagreeView(int userEventId){
		ModelAndView modelView=new ModelAndView();
		modelView.addObject("userEventId",userEventId);
		modelView.setViewName("/WEB-INF/back/studn/unAgree.jsp");
		return modelView;
	}
	
	/**不同意*/
	@RequestMapping("/admin/studn/disagree")
	public @ResponseBody String disagree(int userEventId,String message){
		TuserEvent userEvent = (TuserEvent)service.findObject(TuserEvent.class, userEventId);
		userEvent.setMessage(message);
		userEvent.setStatus("管理员审核中");
		service.saveOrUpdate(userEvent);
		return "true";
	}
	
	@RequestMapping("/admin/studn/uppasswd")
	public @ResponseBody String uppasswd(Integer userId,String password){
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		user.setPassword(password);
		service.saveOrUpdate(user);
		return "true";
	}
	
	@RequestMapping("/admin/studn/uppasswdView")
	public ModelAndView uppasswdView(Integer userId){
		ModelAndView modelview=new ModelAndView();
		Tuser user = (Tuser)service.findObject(Tuser.class, userId);
		modelview.setViewName("/WEB-INF/back/studn/uppassswd.jsp");
		modelview.addObject("user", user);
		return modelview;
	}

	public static String test(){
		try {
			new BigDecimal("ss");
			return "try";
		}catch (Exception e){
			return "catch";
		}finally {
			return "finally";
		}
	}

	public static void main(String[] args) {
		System.out.println(test());
	}
}
