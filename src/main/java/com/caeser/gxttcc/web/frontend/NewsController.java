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

import com.caeser.gxttcc.dao.ProjectsDao;
import com.caeser.gxttcc.entity.Projects;

@Controller
@RequestMapping("/newstt")
public class NewsController {
	@Autowired
	private ProjectsDao projectsDao;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	private String index() {
		return "tt/news";
	}
	
	@RequestMapping(value="/getnews",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getNews(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		if (request.getParameter("typeid") != null) {
			int typeid=Integer.parseInt(request.getParameter("typeid"));
			List<Projects> projectsList=projectsDao.queryIndexTabContentByTypeA(typeid);
			modelMap.put("projectsList", projectsList);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "getnews error");
		}
		return modelMap;
	}
}
