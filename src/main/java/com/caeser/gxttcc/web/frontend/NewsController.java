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

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {//新闻资讯
		return "tt/news";
	}
	@RequestMapping(value = "/joinus", method = RequestMethod.GET)
	private String joinUs() {//联系我们
		return "tt/joinus";
	}

	/*
	 * 通过aab113查询四类新闻资讯----（公司动态1，行业新闻2，通信知识3，企业公告4）
	 */
	@RequestMapping(value = "/getnews", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getNews(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (request.getParameter("typeid") != null) {
			int typeid = Integer.parseInt(request.getParameter("typeid"));
			List<Projects> projectsList = projectsDao.queryIndexTabContentByTypeA(typeid);
			modelMap.put("projectsList", projectsList);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "getnews error");
		}
		return modelMap;
	}
	/*
	 * 通过aab113查询四类新闻资讯----（公司动态1，行业新闻2，通信知识3，企业公告4）
	 */
	@RequestMapping(value = "/getonearticlebyprimkey", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getOneArticleByprimKey(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Projects projectsItem = projectsDao.queryOneArticleByprimKey(id);
			modelMap.put("projectsItem", projectsItem);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "getnews error");
		}
		return modelMap;
	}
}
