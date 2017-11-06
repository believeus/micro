package cn.believeus.admin.controller;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.PaginationUtil.Page;
import cn.believeus.PaginationUtil.Pageable;
import cn.believeus.model.TCategory;
import cn.believeus.model.TCourse;
import cn.believeus.service.MySQLService;


@Controller
public class CourseController {
	@Resource
	private MySQLService service;
	
	@RequestMapping(value="/admin/course/list")
	public ModelAndView listView(Pageable pageable,Integer categoryId){
		ModelAndView modelView=new ModelAndView();
		List<TCategory> categoryList = (List<TCategory>) service.findObjectList(TCategory.class, "parent", 22);
		modelView.addObject("categoryList", categoryList);
		Page<?> pageList=null;
		if (categoryId==null) {
			pageList= service.findObjectPage(TCourse.class, pageable);			
		}else {
			StringBuffer hql=new StringBuffer();
			hql.append("From TCourse where category.id in (select id from TCategory where parent=");
			hql.append(categoryId+")");
			pageList=service.findObjectList(hql.toString(), pageable);
		}
		modelView.addObject("pageList", pageList);
		modelView.addObject("categoryId", categoryId);
		modelView.setViewName("/WEB-INF/back/course/list.jsp");
		return modelView;
	}
	
	@RequestMapping("/admin/course/editView")
	public String editView(int id,Model model){
		TCourse course = (TCourse)service.findObject(TCourse.class, id);
		model.addAttribute("course", course);
		return "/WEB-INF/back/course/edit.jsp";
	}
	
	
	@RequestMapping(value="admin/course/del_course",method=RequestMethod.POST)
	@ResponseBody
	public String del_course(Integer id){
		try {
			service.delete(TCourse.class, id);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping("/admin/course/addView")
	public String addView(){
		return "/WEB-INF/back/course/add.jsp";
	}
	
	@RequestMapping(value="admin/course/edit_course",method=RequestMethod.POST)
	@ResponseBody
	public String add_course(TCourse course,int category_id){
		try {
			course.setCategory(new TCategory(category_id));
			service.saveOrUpdate(course);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
}
