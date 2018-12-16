package com.caeser.gxttcc.web.cps;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caeser.gxttcc.dao.BriefDao;
import com.caeser.gxttcc.entity.Brief;
import com.caeser.gxttcc.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/cps")
public class AdminController {
	@Autowired
	private BriefDao briefDao;

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	private String admin() {// 显示主页
		return "tt/cps/main";
	}

	@RequestMapping(value = "/nav", method = RequestMethod.GET)
	private String nav() {// 左侧标签
		return "tt/cps/nav";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {// 欢迎页面
		return "tt/cps/index";
	}

	@RequestMapping(value = "/brief", method = RequestMethod.GET)
	private String brief() {// 欢迎页面
		return "tt/cps/cabout";
	}

	@RequestMapping(value = "/modifybrief", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyProduct(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Brief brief = new Brief();
		String briefStr = HttpServletRequestUtil.getString(request, "briefStr");
		brief.setAaa102(briefStr);
		try {
			int effectedCount = briefDao.updateBrief(brief);
			if (effectedCount > 0) {
				modelMap.put("success", true);
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "update error");
			}
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}
}
