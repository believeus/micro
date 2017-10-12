package cn.believeus.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.believeus.model.Tadmin;
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
	public String login(Tadmin tadmin) {
		return "/WEB-INF/back/index.jsp";
		
	}
	
	
	
	// 更新管理员的密码
	@RequestMapping(value="/admin/updatePwd")
	public 	@ResponseBody String updatePwd(String newpass,HttpSession session){
		Tadmin admin=(Tadmin)session.getAttribute(Variables.SESSION_USER);
		admin = (Tadmin) service.findObject(Tadmin.class, "id",admin.getId());
		admin.setPassword(newpass);
		service.saveOrUpdate(admin);
		return newpass;
	}
	
	
	
}
