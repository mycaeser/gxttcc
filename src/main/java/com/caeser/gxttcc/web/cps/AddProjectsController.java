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
import com.caeser.gxttcc.dao.NewsDao;
import com.caeser.gxttcc.dao.ProjectsTypeDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.ImgUrl;
import com.caeser.gxttcc.entity.News;
import com.caeser.gxttcc.enums.ProjectsTypeEnum;
import com.caeser.gxttcc.util.GetUrlNameUtil;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;

@Controller
@RequestMapping("/addprojectsarticle")
public class AddProjectsController {
	@Autowired
	private ImgUrlDao imgUrlDao;
	@Autowired
	private ProjectsTypeDao projectsTypeDao;
	@Autowired
	private NewsDao newsDao;
	
	@RequestMapping(value = "/delpjtone", method = RequestMethod.GET)
	private String delPjtOne() {// 显示主页
		return "tt/cps/newscompanypart/deleteprojectsone";
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String admin() {// 显示主页
		return "tt/cps/addarticle";
	}
	@RequestMapping(value = "/modpjtoneview", method = RequestMethod.GET)
	private String modPjtOneView() {// 显示主页
		return "tt/cps/newscompanypart/modprojectsone";
	}
	@RequestMapping(value = "/getonepjtbyprimid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getOnePjtByPrimId(HttpServletRequest request) {// 主键查询一条工程案例
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int pId=HttpServletRequestUtil.getInt(request, "id");
		News oneNews=newsDao.queryNewsByPrimId(pId);
		modelMap.put("newsItem", oneNews);
		return modelMap;
	}
	@RequestMapping(value = "/delpjtarticle", method = RequestMethod.GET)
	private void delPjtArticle(HttpServletRequest request) {// 显示主页
		int typeId=HttpServletRequestUtil.getInt(request, "id");
		String imgName1=HttpServletRequestUtil.getString(request, "img1");
		imgName1=imgName1.substring(PathUtil.filePathInDatabase.length(),imgName1.length());
		String imgName2=HttpServletRequestUtil.getString(request, "img2");
		imgName2=imgName2.substring(PathUtil.filePathInDatabase.length(),imgName2.length());
		String imgName3=HttpServletRequestUtil.getString(request, "img3");
		imgName3=imgName3.substring(PathUtil.filePathInDatabase.length(),imgName3.length());
		String dest = PathUtil.getImgBasePath();
		String relativeAddr = dest + imgName1;
		ImageUtil.deleteFileOrPath(relativeAddr);
		relativeAddr = dest + imgName2;
		ImageUtil.deleteFileOrPath(relativeAddr);
		relativeAddr = dest + imgName3;
		ImageUtil.deleteFileOrPath(relativeAddr);
		int effectedNum=projectsTypeDao.deleteOneProjectsById(typeId);
		if(effectedNum<1) {
			System.out.println("删除一项工程案例失败");
		}
		return ;
	}
	/** 添加一篇工程案例 **/
	@RequestMapping(value = "/addpjtnewarticle", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addPjtNewArticle(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String inputTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String inputDate= HttpServletRequestUtil.getString(request, "inputDate");
		String inputParta= HttpServletRequestUtil.getString(request, "inputParta");
		String inputPartb= HttpServletRequestUtil.getString(request, "inputPartb");
		String inputPartc= HttpServletRequestUtil.getString(request, "inputPartc");
		boolean hrOrProjects=true;
		int typeId=HttpServletRequestUtil.getInt(request, "typeId");
		int hrTypeId=HttpServletRequestUtil.getInt(request, "hrTypeId");
		if(hrTypeId==2) {//如果是添加HR文章
			hrOrProjects=false;
		}
		CommonsMultipartFile itemImga=null,itemImgb=null,itemImgc=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		News newsItem=new News();
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		String newImgUrla=GetUrlNameUtil.getUrlName(imgUrl);
		String newImgUrlb=GetUrlNameUtil.getUrlNameA(newImgUrla);
		String newImgUrlc=GetUrlNameUtil.getUrlNameA(newImgUrlb);
		String fileExtension1=".jpg",fileExtension2=".jpg",fileExtension3=".jpg";
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
					fileExtension1=ImageUtil.getFileExtension(imageHolder.getImageName());
					ImageUtil.copyFile(imageHolder, dest, newImgUrla);
					imageHolder = new ImageHolder(itemImgb.getOriginalFilename(),itemImgb.getInputStream());
					fileExtension2=ImageUtil.getFileExtension(imageHolder.getImageName());
					ImageUtil.copyFile(imageHolder, dest, newImgUrlb);
					imageHolder = new ImageHolder(itemImgc.getOriginalFilename(),itemImgc.getInputStream());
					fileExtension3=ImageUtil.getFileExtension(imageHolder.getImageName());
					ImageUtil.copyFile(imageHolder, dest, newImgUrlc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newImgUrlobj.setImgUrlName(newImgUrlc+fileExtension1);
			int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
			if(effectedNum1<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新imgurl失败");
			}
		}
		newsItem.setAac102(inputTitle);
		newsItem.setAac112(inputDate);
		newsItem.setAac103(inputParta);
		newsItem.setAac104(inputPartb);
		newsItem.setAac105(inputPartc);
		newsItem.setAac109(PathUtil.filePathInDatabase+newImgUrla+fileExtension1);
		newsItem.setAac110(PathUtil.filePathInDatabase+newImgUrlb+fileExtension2);
		newsItem.setAac111(PathUtil.filePathInDatabase+newImgUrlc+fileExtension3);
		if(hrOrProjects) {
			newsItem.setAac113(1);//工程案例1 招聘信息2
			newsItem.setAac115(typeId);
			//这里根据分类的编号设置的枚举类型是提前写好的，不会随数据库里的改变
			newsItem.setAac116(ProjectsTypeEnum.stateOf(typeId).getStateInfo());
		}else {
			newsItem.setAac113(2);
		}
		
		int effectedNum=projectsTypeDao.insertOneProjects(newsItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "添加新的工程案例文章一项失败");
		}
		return modelMap;
	}
	/** 修改一篇工程案例 **/
	@RequestMapping(value = "/modpjtarticleone", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modPjtArticleOne(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String inputTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String inputDate= HttpServletRequestUtil.getString(request, "inputDate");
		String inputParta= HttpServletRequestUtil.getString(request, "inputParta");
		String inputPartb= HttpServletRequestUtil.getString(request, "inputPartb");
		String inputPartc= HttpServletRequestUtil.getString(request, "inputPartc");
		int typeId=HttpServletRequestUtil.getInt(request, "typeId");
		CommonsMultipartFile itemImga=null,itemImgb=null,itemImgc=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		News newsItem=new News();
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		String newImgUrla=GetUrlNameUtil.getUrlName(imgUrl);
		String newImgUrlb=GetUrlNameUtil.getUrlNameA(newImgUrla);
		String newImgUrlc=GetUrlNameUtil.getUrlNameA(newImgUrlb);
		String fileExtension1=".jpg",fileExtension2=".jpg",fileExtension3=".jpg";
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
					fileExtension1=ImageUtil.getFileExtension(imageHolder.getImageName());
					ImageUtil.copyFile(imageHolder, dest, newImgUrla);
					imageHolder = new ImageHolder(itemImgb.getOriginalFilename(),itemImgb.getInputStream());
					fileExtension2=ImageUtil.getFileExtension(imageHolder.getImageName());
					ImageUtil.copyFile(imageHolder, dest, newImgUrlb);
					imageHolder = new ImageHolder(itemImgc.getOriginalFilename(),itemImgc.getInputStream());
					fileExtension3=ImageUtil.getFileExtension(imageHolder.getImageName());
					ImageUtil.copyFile(imageHolder, dest, newImgUrlc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newImgUrlobj.setImgUrlName(newImgUrlc+fileExtension1);
			int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
			if(effectedNum1<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新imgurl失败");
			}
		}
		newsItem.setAac102(inputTitle);
		newsItem.setAac112(inputDate);
		newsItem.setAac103(inputParta);
		newsItem.setAac104(inputPartb);
		newsItem.setAac105(inputPartc);
		newsItem.setAac115(typeId);
		//这里根据分类的编号设置的枚举类型是提前写好的，不会随数据库里的改变
		newsItem.setAac116(ProjectsTypeEnum.stateOf(typeId).getStateInfo());
		newsItem.setAac109(PathUtil.filePathInDatabase+newImgUrla+fileExtension1);
		newsItem.setAac110(PathUtil.filePathInDatabase+newImgUrlb+fileExtension2);
		newsItem.setAac111(PathUtil.filePathInDatabase+newImgUrlc+fileExtension3);
		newsItem.setAac113(1);//工程案例1 招聘信息2
		int effectedNum=projectsTypeDao.insertOneProjects(newsItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "添加新的工程案例文章一项失败");
		}
		return modelMap;
	}
}
