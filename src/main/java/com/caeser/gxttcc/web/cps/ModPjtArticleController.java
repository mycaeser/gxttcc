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

import com.caeser.gxttcc.dao.NewsDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.News;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/mod")
public class ModPjtArticleController {
	@Autowired
	private NewsDao newsDao;
	
	/** 用于修改案例管理文章**/
	@RequestMapping(value = "/modifypjtarticle", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyPjtArticle(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		CommonsMultipartFile cpyImg1 = null,cpyImg2 = null,cpyImg3 = null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		int cpyId=HttpServletRequestUtil.getInt(request, "id");
		String cpyTitie=HttpServletRequestUtil.getString(request, "inputTitle");
		String cpyContent1=HttpServletRequestUtil.getString(request, "inputContent1");
		String cpyContent2=HttpServletRequestUtil.getString(request, "inputContent2");
		String cpyContent3=HttpServletRequestUtil.getString(request, "inputContent3");
		String cpyDate=HttpServletRequestUtil.getString(request, "inputDate");
		News newsById=newsDao.queryNewsByPrimId(cpyId);
		String fileExtension1=".jpg",fileExtension2=".jpg",fileExtension3=".jpg";
		String cpyImgName1=newsById.getAac109();
		fileExtension1=cpyImgName1.substring(cpyImgName1.indexOf("."),cpyImgName1.length());
		cpyImgName1=cpyImgName1.substring(PathUtil.filePathInDatabase.length(),cpyImgName1.indexOf("."));
		String cpyImgName2=newsById.getAac110();
		fileExtension2=cpyImgName2.substring(cpyImgName2.indexOf("."),cpyImgName2.length());
		cpyImgName2=cpyImgName2.substring(PathUtil.filePathInDatabase.length(),cpyImgName2.indexOf("."));
		String cpyImgName3=newsById.getAac111();
		fileExtension3=cpyImgName3.substring(cpyImgName3.indexOf("."),cpyImgName3.length());
		cpyImgName3=cpyImgName3.substring(PathUtil.filePathInDatabase.length(),cpyImgName3.indexOf("."));
		
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			cpyImg1 = (CommonsMultipartFile) multipartHttpServletRequest.getFile("img1");
			cpyImg2 = (CommonsMultipartFile) multipartHttpServletRequest.getFile("img2");
			cpyImg3 = (CommonsMultipartFile) multipartHttpServletRequest.getFile("img3");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为null");
		}
		if(cpyImg1!=null&&cpyImg2!=null&&cpyImg3!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(cpyImg1.getOriginalFilename(),cpyImg1.getInputStream());
				String dest = PathUtil.getImgBasePath();
				
				ImageUtil.copyFileA(imageHolder, dest,cpyImgName1,fileExtension1);
				imageHolder=new ImageHolder(cpyImg2.getOriginalFilename(),cpyImg2.getInputStream());
				ImageUtil.copyFileA(imageHolder, dest,cpyImgName2,fileExtension2);
				imageHolder=new ImageHolder(cpyImg3.getOriginalFilename(),cpyImg3.getInputStream());
				ImageUtil.copyFileA(imageHolder, dest,cpyImgName3,fileExtension3);
				
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		if(cpyTitie!=null&&cpyContent1!=null&&cpyContent2!=null&&cpyContent3!=null&&cpyDate!=null) {
			News tmpNews=new News();
			tmpNews.setAac101(cpyId);
			tmpNews.setAac102(cpyTitie);
			tmpNews.setAac103(cpyContent1);
			tmpNews.setAac104(cpyContent2);
			tmpNews.setAac105(cpyContent3);
			tmpNews.setAac112(cpyDate);
			if(cpyImg1!=null&&cpyImg2!=null&&cpyImg3!=null) {
				tmpNews.setAac109(PathUtil.filePathInDatabase+cpyImgName1+fileExtension1);
				tmpNews.setAac110(PathUtil.filePathInDatabase+cpyImgName2+fileExtension2);
				tmpNews.setAac111(PathUtil.filePathInDatabase+cpyImgName3+fileExtension3);
			}
			int effectedNum=newsDao.updateNewsArticle(tmpNews);
			if(effectedNum<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新工程案例失败");
			}
		}
		
		return modelMap;
	}
}
