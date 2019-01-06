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

import com.caeser.gxttcc.dao.HonorDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.Honor;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class HonorController {
	@Autowired
	private HonorDao honorDao;
	
	@RequestMapping(value = "/gethonorbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getHonorById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int honorId = HttpServletRequestUtil.getInt(request, "id");
		Honor honorItem=honorDao.queryHonorById(honorId);
		if(honorItem!=null) {
			modelMap.put("honorItem", honorItem);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "获取荣誉资质一项失败");
		}
		return modelMap;
	}
	@RequestMapping(value = "/deletehonorbyid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> deleteHonorById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int honorId = HttpServletRequestUtil.getInt(request, "id");
		
		Honor honorItem=new Honor();
		honorItem=honorDao.queryHonorById(honorId);
		if(honorItem!=null) {
			String honorImgName=honorItem.getAaa503();
			honorImgName=honorImgName.substring(7,honorImgName.lastIndexOf("."));
			String dest = PathUtil.getImgBasePath();
			String relativeAddr = dest + honorImgName + ".jpg";
			ImageUtil.deleteFileOrPath(relativeAddr);
		}
		int effectedNum=honorDao.deleteHonerById(honorId);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "删除荣誉资质一项失败");
		}
		return modelMap;
	}
	@RequestMapping(value = "/updatehonorbyid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateHonorById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int honorId = HttpServletRequestUtil.getInt(request, "honorId");
		String honorText=HttpServletRequestUtil.getString(request, "honorText");
		String honorTitle= HttpServletRequestUtil.getString(request, "honorTitle");
		String honorDate= HttpServletRequestUtil.getString(request, "honorDate");
		CommonsMultipartFile honorImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Honor honor=new Honor();
		honor.setAaa501(honorId);
		honor.setAaa502(honorTitle);
		honor.setAaa505(honorDate);
		honor.setAaa504(honorText);
		int effectedNum=honorDao.updateHonor(honor);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "更新荣誉资质一项失败");
		}
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			honorImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("teamFile");
		}
		if(honorImg!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(honorImg.getOriginalFilename(),honorImg.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName=honorDao.queryHonorById(honorId).getAaa503();
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
