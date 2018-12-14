package com.caeser.gxttcc.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caeser.gxttcc.dao.NewsDao;
import com.caeser.gxttcc.entity.News;

@Controller
@RequestMapping("/projectstt")
public class ProjectsController {
	@Autowired
	private NewsDao newsDao;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	private String index() {
		return "tt/projects";
	}
	
	@RequestMapping(value="/getprojects",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getProjects(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		if (request.getParameter("typeid") != null) {
			int typeid=Integer.parseInt(request.getParameter("typeid"));
			List<News> newsList=newsDao.queryNewsByType(typeid);
			modelMap.put("newsList", newsList);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "getprojects error");
		}
		return modelMap;
	}
}
