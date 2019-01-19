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

import com.caeser.gxttcc.dao.ProjectsDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.Projects;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/cps")
public class ModCpyArticleController {
	@Autowired
	private ProjectsDao projectsDao;
	/**
	 * 修改新闻资讯里的公司动态
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/modifycompanyarticle", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyCompanyArticle(HttpServletRequest request) {
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
		Projects projectsWhichId=projectsDao.queryOneArticleByprimKey(cpyId);
		String fileExtension1=".jpg",fileExtension2=".jpg",fileExtension3=".jpg";
		String cpyImgName1=projectsWhichId.getAab109();
		fileExtension1=cpyImgName1.substring(cpyImgName1.indexOf("."),cpyImgName1.length());
		cpyImgName1=cpyImgName1.substring(PathUtil.filePathInDatabase.length(),cpyImgName1.indexOf("."));
		String cpyImgName2=projectsWhichId.getAab110();
		fileExtension2=cpyImgName2.substring(cpyImgName2.indexOf("."),cpyImgName2.length());
		cpyImgName2=cpyImgName2.substring(PathUtil.filePathInDatabase.length(),cpyImgName2.indexOf("."));
		String cpyImgName3=projectsWhichId.getAab111();
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
			Projects tmpProjects=new Projects();
			tmpProjects.setAab101(cpyId);
			tmpProjects.setAab102(cpyTitie);
			tmpProjects.setAab103(cpyContent1);
			tmpProjects.setAab104(cpyContent2);
			tmpProjects.setAab105(cpyContent3);
			tmpProjects.setAab112(cpyDate);
			if(cpyImg1!=null&&cpyImg2!=null&&cpyImg3!=null) {
				tmpProjects.setAab109(PathUtil.filePathInDatabase+cpyImgName1+fileExtension1);
				tmpProjects.setAab110(PathUtil.filePathInDatabase+cpyImgName2+fileExtension2);
				tmpProjects.setAab111(PathUtil.filePathInDatabase+cpyImgName3+fileExtension3);
			}
			int effectedNum=projectsDao.updateOneArticleByprimKey(tmpProjects);
			if(effectedNum<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新新闻资讯失败");
			}
		}
		
		return modelMap;
	}
}
