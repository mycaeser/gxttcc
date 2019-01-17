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
import com.caeser.gxttcc.dao.ImgUrlDao;
import com.caeser.gxttcc.dao.TeamDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.Honor;
import com.caeser.gxttcc.entity.ImgUrl;
import com.caeser.gxttcc.entity.Team;
import com.caeser.gxttcc.util.GetUrlNameUtil;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;


@Controller
@RequestMapping("/addarticlemodel")
public class AddArticleModelController {
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private ImgUrlDao imgUrlDao;
	@Autowired
	private HonorDao honorDao;
	
	@RequestMapping(value = "/addteam", method = RequestMethod.GET)
	private String addTeam() {// 显示主页
		return "tt/cps/addteamarticle";
	}
	@RequestMapping(value = "/addteam", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addTeam(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String teamTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String teamDate= HttpServletRequestUtil.getString(request, "inputDate");
		CommonsMultipartFile teamImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Team teamItem=new Team();
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		String newImgUrl=GetUrlNameUtil.getUrlName(imgUrl);
		String fileExtension=".jpg";
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			teamImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("teamImg");
			String dest = PathUtil.getImgBasePath();
			ImageHolder imageHolder;
			ImgUrl newImgUrlobj=new ImgUrl();
			if(teamImg!=null) {
				try {
					imageHolder = new ImageHolder(teamImg.getOriginalFilename(),teamImg.getInputStream());
					ImageUtil.copyFile(imageHolder, dest, newImgUrl);
					fileExtension=ImageUtil.getFileExtension(imageHolder.getImageName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newImgUrlobj.setImgUrlName(newImgUrl+fileExtension);
			int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
			if(effectedNum1<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新imgurl失败");
			}
		}
		teamItem.setAaa402(teamTitle);
		teamItem.setAaa403(PathUtil.filePathInDatabase+newImgUrl+fileExtension);
		teamItem.setAaa404(teamDate);
		int effectedNum=teamDao.insertTeam(teamItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "添加团队一项失败");
		}
		return modelMap;
	}
	@RequestMapping(value = "/addhonor", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addHonor(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		String honorTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String honorDate= HttpServletRequestUtil.getString(request, "inputDate");
		String honorText= HttpServletRequestUtil.getString(request, "inputText");
		CommonsMultipartFile honorImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Honor honorItem=new Honor();
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		String newImgUrl=GetUrlNameUtil.getUrlName(imgUrl);
		String fileExtension=".jpg";
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			honorImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("honorImg");
			String dest = PathUtil.getImgBasePath();
			ImageHolder imageHolder;
			ImgUrl newImgUrlobj=new ImgUrl();
			if(honorImg!=null) {
				try {
					imageHolder = new ImageHolder(honorImg.getOriginalFilename(),honorImg.getInputStream());
					ImageUtil.copyFile(imageHolder, dest, newImgUrl);
					fileExtension=ImageUtil.getFileExtension(imageHolder.getImageName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			newImgUrlobj.setImgUrlName(newImgUrl+fileExtension);
			int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
			if(effectedNum1<1) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "更新imgurl失败");
			}
		}
		honorItem.setAaa502(honorTitle);
		honorItem.setAaa503(PathUtil.filePathInDatabase+newImgUrl+fileExtension);
		honorItem.setAaa504(honorText);
		honorItem.setAaa505(honorDate);
		int effectedNum=honorDao.insertHonor(honorItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "添加团队一项失败");
		}
		return modelMap;
	}
}
