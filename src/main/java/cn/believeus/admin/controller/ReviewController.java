package cn.believeus.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		List<?> userEventList = service.findObjectList(TuserEvent.class, 10);
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

	
	
	@RequestMapping("/admin/review/update")
	@ResponseBody
	 public String update(Integer userEventId, String status, int liveValue,int learnValue) {
		TuserEvent userEvent = (TuserEvent) service.findObject(TuserEvent.class, userEventId);
		userEvent.setStatus(status);
		switch (liveValue) {
		case 0:
			userEvent.setLearnValue(learnValue);
			break;
		default:
			userEvent.setLiveValue(liveValue);
			break;
		}
		service.saveOrUpdate(userEvent);
		if (status.equals("酌情增减积分") || status.equals("证据确凿定案")) {
			int userId = userEvent.getUserId();
			Tuser user = (Tuser) service.findObject(Tuser.class, userId);
			switch (liveValue) {
			case 0:
				user.setLearnValue(user.getLearnValue()+learnValue);
				break;
			default:
				user.setLiveValue(user.getLiveValue()+liveValue);
				break;
			}
			service.saveOrUpdate(user);
		}
		return "true";
	}

}
