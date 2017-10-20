package cn.believeus.admin.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.believeus.model.Tuser;
import cn.believeus.model.TuserEvent;
import cn.believeus.service.MySQLService;

@Controller
public class ReviewController {
	@Resource
	private MySQLService service;

	@RequestMapping("/admin/review/list")
	public ModelAndView list() {
		ModelAndView modelView = new ModelAndView();
		String hql="from TuserEvent e where e.type in ('rule-live','rule-learn')";
		List<?> userEventList = service.findObjectList(hql, 10);
		modelView.addObject("userEventList", userEventList);
		modelView.setViewName("/WEB-INF/back/review/reviewlist.jsp");
		return modelView;
	}

	@RequestMapping("/admin/review/editview")
	public ModelAndView editview(int userEventId) {
		ModelAndView modelView = new ModelAndView();
		TuserEvent userEvent = (TuserEvent) service.findObject(TuserEvent.class, userEventId);
		modelView.setViewName("/WEB-INF/back/review/reviewedit.jsp");
		modelView.addObject("userEvent", userEvent);
		return modelView;
	}

	@ModelAttribute
	public void model(@RequestParam(required=false) Integer userEventId,Map<String, TuserEvent> map,HttpServletRequest req){
		String uri = req.getRequestURI();
		if(uri.contains("admin/review/update")){
			TuserEvent userEvent = (TuserEvent)service.findObject(TuserEvent.class, userEventId);
			map.put("userEvent", userEvent);
		}
	}
	
	@RequestMapping("/admin/review/update")
	@ResponseBody
	 public String update(@ModelAttribute("userEvent") TuserEvent userEvent) {
		String status = userEvent.getStatus();
		int value=userEvent.getValue();
		if (status.equals("酌情增减积分") || status.equals("证据确凿定案")) {
			int userId = userEvent.getUserId();
			Tuser user = (Tuser) service.findObject(Tuser.class, userId);
			String type = userEvent.getType();
			if(type.equals("live")){user.setLearnValue(user.getLearnValue()+value);}
			else{user.setLiveValue(user.getLiveValue()+value);}
			service.saveOrUpdate(user);
		}
		service.saveOrUpdate(userEvent);
		return "true";
	}

}
