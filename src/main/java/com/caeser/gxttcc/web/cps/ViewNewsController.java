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
import com.caeser.gxttcc.entity.Honor;
import com.caeser.gxttcc.entity.ImgUrl;
import com.caeser.gxttcc.util.GetUrlNameUtil;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class ViewNewsController {

	@RequestMapping(value = "/viewparta", method = RequestMethod.GET)
	private String viewPartA() {// 公司动态
		return "tt/cps/cnews";
	}

	@RequestMapping(value = "/cprojects", method = RequestMethod.GET)
	private String viewProjects() {//
		return "tt/cps/cprojects";
	}
	@RequestMapping(value = "/caddnewsarticle", method = RequestMethod.GET)
	private String viewNews() {//跳转到添加新闻资讯模板页面
		return "tt/cps/addnewsarticle";
	}
	@RequestMapping(value = "/addnewsbyid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addNewsById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
//		String honorTitle= HttpServletRequestUtil.getString(request, "inputTitle");
//		String honorDate= HttpServletRequestUtil.getString(request, "inputDate");
//		String honorText= HttpServletRequestUtil.getString(request, "inputText");
//		CommonsMultipartFile honorImg=null;
//		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
//				request.getSession().getServletContext());
//		Honor honorItem=new Honor();
//		ImgUrl imgUrl=imgUrlDao.queryUrl();
//		String newImgUrl=GetUrlNameUtil.getUrlName(imgUrl);
//		String fileExtension=".jpg";
//		if (commonsMultipartResolver.isMultipart(request)) {
//			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//			honorImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("honorImg");
//			String dest = PathUtil.getImgBasePath();
//			ImageHolder imageHolder;
//			ImgUrl newImgUrlobj=new ImgUrl();
//			if(honorImg!=null) {
//				try {
//					imageHolder = new ImageHolder(honorImg.getOriginalFilename(),honorImg.getInputStream());
//					ImageUtil.copyFile(imageHolder, dest, newImgUrl);
//					fileExtension=ImageUtil.getFileExtension(imageHolder.getImageName());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			newImgUrlobj.setImgUrlName(newImgUrl+fileExtension);
//			int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
//			if(effectedNum1<1) {
//				modelMap.put("success", false);
//				modelMap.put("errMsg", "更新imgurl失败");
//			}
//		}
//		honorItem.setAaa502(honorTitle);
//		honorItem.setAaa503(PathUtil.filePathInDatabase+newImgUrl+fileExtension);
//		honorItem.setAaa504(honorText);
//		honorItem.setAaa505(honorDate);
//		int effectedNum=honorDao.insertHonor(honorItem);
//		if(effectedNum<1) {
//			modelMap.put("success", false);
//			modelMap.put("errMsg", "添加团队一项失败");
//		}
		return modelMap;
	}
}
