package com.caeser.gxttcc.web.cps;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class OrgAndPartnerController {
	
	@RequestMapping(value = "/updateorgimg", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateHonorById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CommonsMultipartFile honorImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			honorImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("orgFile");
		}
		if(honorImg!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(honorImg.getOriginalFilename(),honorImg.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName="taitongjj08";
				ImageUtil.generateNormalImg(imageHolder, dest,tmpName);
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		return modelMap;
	}
}
