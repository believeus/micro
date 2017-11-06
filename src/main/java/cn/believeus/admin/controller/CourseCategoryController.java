package cn.believeus.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sun.tracing.dtrace.ModuleAttributes;

import cn.believeus.model.TCategory;
import cn.believeus.model.TuserEvent;
import cn.believeus.service.MySQLService;
import cn.believeus.util.TreeModel;


@Controller
public class CourseCategoryController {
	@Resource
	private MySQLService service;
	
	@RequestMapping(value="/admin/course/category")
	public String listView(){
		return "/WEB-INF/back/course/categoryList.jsp";
	}

	@RequestMapping(value="/admin/course/loadTree",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String loadTree(){
        List<TCategory> objectList = (List<TCategory>) service.findObjectList(TCategory.class);
        List<TreeModel> list = new ArrayList<TreeModel>();
        for (TCategory category : objectList) {
        	if (category.getName().equals("全部")) {
        		list.add(new TreeModel(category.getId(),category.getParent(),category.getName(),true));
			}else {
				list.add(new TreeModel(category.getId(),category.getParent(),category.getName(),false));				
			}
		}
        String jsonString = JSON.toJSONString(list);
        System.out.println(jsonString);
		return jsonString;
	}
	
	@ModelAttribute
	public void model(@RequestParam(required=false) Integer id,Map<String, TCategory> map,HttpServletRequest req){
		String uri = req.getRequestURI();
		if(uri.contains("/admin/course/up_category")){
			TCategory category = (TCategory)service.findObject(TCategory.class, id);
			map.put("category", category);
		}
	}
	
	@RequestMapping(value="/admin/course/up_category",method=RequestMethod.POST)
	@ResponseBody
	public String up_category(@ModelAttribute("category")TCategory category){
		try {
			category.setEditTime(new Date().getTime());
			service.saveOrUpdate(category);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value="admin/course/del_category",method=RequestMethod.POST)
	@ResponseBody
	public String del_category(Integer id){
		try {
			List<Integer> ids = new ArrayList<Integer>();
			for (Object object : service.findObjectList(TCategory.class, "parent", id)) {
			    TCategory category=(TCategory)object;
				ids.add(category.getId());
			}
			ids.add(id);
			service.delete(TCategory.class, ids);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}
	
	@RequestMapping(value="admin/course/add_category",method=RequestMethod.POST)
	@ResponseBody
	public String add_category(TCategory category){
		try {
			service.saveOrUpdate(category);
			return String.valueOf(category.getId()); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "-1";
	}
	
}
