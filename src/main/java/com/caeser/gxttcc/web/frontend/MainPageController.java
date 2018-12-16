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

import com.caeser.gxttcc.dao.AddressDao;
import com.caeser.gxttcc.dao.HonorDao;
import com.caeser.gxttcc.dao.NewsDao;
import com.caeser.gxttcc.entity.Address;
import com.caeser.gxttcc.entity.Brief;
import com.caeser.gxttcc.entity.Honor;
import com.caeser.gxttcc.entity.News;
import com.caeser.gxttcc.entity.Projects;
import com.caeser.gxttcc.service.BriefService;
import com.caeser.gxttcc.service.ProjectsService;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private BriefService briefService;
	@Autowired
	private ProjectsService projectsService;
	@Autowired
	private HonorDao honorDao;
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private AddressDao addressDao;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {// 显示主页
		return "tt/index";
	}

	/**
	 * 返回简介信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getbriefintroduction", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getBriefIntroduction(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Brief brief = briefService.queryBrief();
		if (brief != null) {
			modelMap.put("briefIntroduction", brief.getAaa102());
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "briefIntroduction-sql-error");
		}
		return modelMap;
	}

	/**
	 * 返回主页4个tab图片URL
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getindextabimg", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getIndexTabImg(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (request.getParameter("tabid") != null) {
			int tabid = Integer.parseInt(request.getParameter("tabid").toString());
			Projects targer = projectsService.queryIndexTabImgById(tabid);
			modelMap.put("tabUrl", targer.getAab109());
		}
		return modelMap;
	}

	/**
	 * 返回主页4个tab里的内容标题和日期
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getindextabcontent", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getIndexTabContent(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (request.getParameter("type") != null) {
			int type = Integer.parseInt(request.getParameter("type").toString());
			List<Projects> projectsList = projectsService.queryIndexTabContentByType(type);
			modelMap.put("projectsList", projectsList);
		}
		return modelMap;
	}

	/**
	 * 返回主页荣誉资质
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gethonor", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getHonor(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();

		List<Honor> honorList = honorDao.queryHonor();
		modelMap.put("honorList", honorList);
		return modelMap;
	}

	/**
	 * 返回案例
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getbottoma", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getBottoma(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (request.getParameter("bottomaid") != null) {
			int bottomaid = Integer.parseInt(request.getParameter("bottomaid").toString());
			List<News> newsList = newsDao.queryIndexBottomItems(bottomaid);
			modelMap.put("newsList", newsList);
		}
		return modelMap;
	}
	/**
	 * 查询主公司地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getaddress", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getAddress(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		if (request.getParameter("addid") != null) {
			int addid = Integer.parseInt(request.getParameter("addid").toString());
			Address add = addressDao.queryAddByID(addid);
			modelMap.put("add", add);
		}
		return modelMap;
	}
	/**
	 * 查询分公司地址
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getaddressot", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getAddressOt(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();	
		List<Address> addList = addressDao.queryAddot();
		modelMap.put("addList", addList);
		return modelMap;
	}
}
