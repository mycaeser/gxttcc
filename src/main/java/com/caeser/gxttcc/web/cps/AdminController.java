package com.caeser.gxttcc.web.cps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.caeser.gxttcc.dao.AboutUsDao;
import com.caeser.gxttcc.dao.BriefDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.AboutUs;
import com.caeser.gxttcc.entity.Brief;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class AdminController {
	@Autowired
	private BriefDao briefDao;
	@Autowired
	private AboutUsDao aboutUsDao;

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
	private Map<String, Object> modifytBrief(HttpServletRequest request) {
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
	@RequestMapping(value = "/modifybriefa", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyAbout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CommonsMultipartFile briefImg = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String briefTxt=HttpServletRequestUtil.getString(request, "briefText");
		if(briefTxt!=null&&briefTxt.length()<2000) {
			AboutUs tmpAbout=new AboutUs();
			tmpAbout.setAaa202(briefTxt);
			int effectedNum=aboutUsDao.updateAboutUsa(tmpAbout);
			if(effectedNum<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新简介失败");
			}
		}
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			briefImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("briefImgFile");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为null");
		}
		if(briefImg!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(briefImg.getOriginalFilename(),briefImg.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName=aboutUsDao.queryAboutUs().getAaa204();
				tmpName=tmpName.substring(7,tmpName.lastIndexOf("."));
				ImageUtil.generateNormalImg(imageHolder, dest,tmpName);
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		return modelMap;
	}
}
