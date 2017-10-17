package cn.believeus.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.believeus.model.Tuser;
import cn.believeus.service.MySQLService;
import cn.believeus.variables.Variables;

@Controller
public class AdminController {
	
	@Resource
	private MySQLService service;

	
	@RequestMapping(value="/admin/loginView")
	public String loginView(){
		return "/WEB-INF/back/login.jsp";
	}
	
	
	@RequestMapping(value = "/admin/login")
	public String login(Tuser user) {
		return "/WEB-INF/back/index.jsp";
		
	}
	// 更新管理员的密码
	@RequestMapping(value="/admin/updatePwd")
	public 	@ResponseBody String updatePwd(String newpass,HttpSession session){
		Tuser user=(Tuser)session.getAttribute(Variables.SESSION_USER);
		user = (Tuser) service.findObject(Tuser.class, "id",user.getId());
		user.setPassword(newpass);
		service.saveOrUpdate(user);
		return newpass;
	}
	
	@RequestMapping("admin/logout")
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:/admin/loginView.jhtml";
	}
	
	@RequestMapping("admin/welcome")
	public String welcome(){
		return "/WEB-INF/back/welcome.jsp";
	}
	
}
