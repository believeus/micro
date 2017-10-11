package cn.believeus.admin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.believeus.model.Admin;
import cn.believeus.service.MySQLService;

@Controller
public class AdminController {
	
	@Resource
	private MySQLService mysqlService;

	@RequestMapping(value = "/admin/manager")
	public String manager() {
		return "/WEB-INF/back/index.jsp";
	}
	
	@RequestMapping(value="/admin/login")
	public String login(){
		return "/WEB-INF/back/login.jsp";
	}
	
	// 更新管理员的密码
	@RequestMapping(value="/admin/updatePwd")
	public 	@ResponseBody String updatePwd(String newpass,HttpSession session){
		Admin admin=(Admin)session.getAttribute("sessionUser");
		admin = (Admin) mysqlService.findObject(Admin.class, "id",admin.getId());
		admin.setPassword(newpass);
		mysqlService.saveOrUpdate(admin);
		return newpass;
	}
	
	
	
}
