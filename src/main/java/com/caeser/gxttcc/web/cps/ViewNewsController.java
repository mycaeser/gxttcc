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

import com.caeser.gxttcc.dao.ImgUrlDao;
import com.caeser.gxttcc.dao.ProjectsDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.Honor;
import com.caeser.gxttcc.entity.ImgUrl;
import com.caeser.gxttcc.entity.Projects;
import com.caeser.gxttcc.util.GetUrlNameUtil;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class ViewNewsController {
	@Autowired
	private ImgUrlDao imgUrlDao;
	@Autowired
	private ProjectsDao projectsDao;

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
		String inputTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String inputDate= HttpServletRequestUtil.getString(request, "inputDate");
		String inputParta= HttpServletRequestUtil.getString(request, "inputParta");
		String inputPartb= HttpServletRequestUtil.getString(request, "inputPartb");
		String inputPartc= HttpServletRequestUtil.getString(request, "inputPartc");
		String partId=HttpServletRequestUtil.getString(request, "partid");
		CommonsMultipartFile itemImga=null,itemImgb=null,itemImgc=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Projects projectsItem=new Projects();
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		String newImgUrla=GetUrlNameUtil.getUrlName(imgUrl);
		String newImgUrlb=GetUrlNameUtil.getUrlNameA(newImgUrla);
		String newImgUrlc=GetUrlNameUtil.getUrlNameA(newImgUrlb);
		String fileExtension=".jpg";
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			itemImga = (CommonsMultipartFile) multipartHttpServletRequest.getFile("clImg1");
			itemImgb = (CommonsMultipartFile) multipartHttpServletRequest.getFile("clImg2");
			itemImgc = (CommonsMultipartFile) multipartHttpServletRequest.getFile("clImg3");
			String dest = PathUtil.getImgBasePath();
			ImageHolder imageHolder;
			ImgUrl newImgUrlobj=new ImgUrl();
			if(itemImga!=null&&itemImgb!=null&&itemImgc!=null) {
				try {
					imageHolder = new ImageHolder(itemImga.getOriginalFilename(),itemImga.getInputStream());
					ImageUtil.copyFile(imageHolder, dest, newImgUrla);
					imageHolder = new ImageHolder(itemImgb.getOriginalFilename(),itemImgb.getInputStream());
					ImageUtil.copyFile(imageHolder, dest, newImgUrlb);
					imageHolder = new ImageHolder(itemImgc.getOriginalFilename(),itemImgc.getInputStream());
					ImageUtil.copyFile(imageHolder, dest, newImgUrlc);
					fileExtension=ImageUtil.getFileExtension(imageHolder.getImageName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newImgUrlobj.setImgUrlName(newImgUrlc+fileExtension);
			int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
			if(effectedNum1<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新imgurl失败");
			}
		}
		projectsItem.setAab102(inputTitle);
		projectsItem.setAab112(inputDate);
		projectsItem.setAab103(inputParta);
		projectsItem.setAab104(inputPartb);
		projectsItem.setAab105(inputPartc);
		projectsItem.setAab113(partId);
		projectsItem.setAab113("1");
		projectsItem.setAab109(PathUtil.filePathInDatabase+newImgUrla+fileExtension);
		projectsItem.setAab110(PathUtil.filePathInDatabase+newImgUrlb+fileExtension);
		projectsItem.setAab111(PathUtil.filePathInDatabase+newImgUrlc+fileExtension);
		int effectedNum=projectsDao.insertArticle(projectsItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "添加新的公司动态文章一项失败");
		}
		return modelMap;
	}
}
