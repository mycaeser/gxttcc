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

import com.caeser.gxttcc.dao.CultureDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.Culture;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class IndexCultureController {
	@Autowired
	private CultureDao cultureDao;
	
	
	@RequestMapping(value = "/modifyindexculture", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyIndexCulture(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CommonsMultipartFile cultureImg1=null,cultureImg2=null,cultureImg3 = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String tmpfTxt=HttpServletRequestUtil.getString(request, "inputTitle");
		Culture culture=new Culture();
		culture.setAaa302(tmpfTxt);
		tmpfTxt=HttpServletRequestUtil.getString(request, "inputStr1");
		culture.setAaa303(tmpfTxt);
		tmpfTxt=HttpServletRequestUtil.getString(request, "inputStr2");
		culture.setAaa304(tmpfTxt);
		tmpfTxt=HttpServletRequestUtil.getString(request, "inputStr3");
		culture.setAaa305(tmpfTxt);
		tmpfTxt=HttpServletRequestUtil.getString(request, "inputStr4");
		culture.setAaa312(tmpfTxt);
		if(tmpfTxt!=null) {
			int effectedNum=cultureDao.updateCulture(culture);
			if(effectedNum<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新企业文化失败");
			}
		}
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			cultureImg1 = (CommonsMultipartFile) multipartHttpServletRequest.getFile("clImg1");
			cultureImg2 = (CommonsMultipartFile) multipartHttpServletRequest.getFile("clImg2");
			cultureImg3 = (CommonsMultipartFile) multipartHttpServletRequest.getFile("clImg3");
		} 
		if(cultureImg1!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(cultureImg1.getOriginalFilename(),cultureImg1.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName=cultureDao.queryCulture().getAaa309();
				tmpName=tmpName.substring(7,tmpName.lastIndexOf("."));
				ImageUtil.generateNormalImg(imageHolder, dest,tmpName);
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		if(cultureImg2!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(cultureImg2.getOriginalFilename(),cultureImg2.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName=cultureDao.queryCulture().getAaa310();
				tmpName=tmpName.substring(7,tmpName.lastIndexOf("."));
				ImageUtil.generateNormalImg(imageHolder, dest,tmpName);
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		if(cultureImg3!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(cultureImg3.getOriginalFilename(),cultureImg3.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName=cultureDao.queryCulture().getAaa311();
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
