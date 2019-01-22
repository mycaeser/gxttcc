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
	
	/** /gxttcc/addarticlemodel/addteam?a=1 管理团队
	 *  /gxttcc/addarticlemodel/addteam?a=1 荣誉资质
	 * **/
	@RequestMapping(value = "/addteam", method = RequestMethod.GET)
	private String addTeam() {// 显示主页
		return "tt/cps/addteamarticle";
	}
	/** 添加一篇管理团队文章 **/
	@RequestMapping(value = "/addteam", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addTeam(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前端传来的 标题 和日期
		String teamTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String teamDate= HttpServletRequestUtil.getString(request, "inputDate");
		CommonsMultipartFile teamImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Team teamItem=new Team();
		//查询当前的图片URL字符串
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		//URL字符串自增1
		String newImgUrl=GetUrlNameUtil.getUrlName(imgUrl);
		//初始化扩展名
		String fileExtension=".jpg";
		if (commonsMultipartResolver.isMultipart(request)) {//先判断图片是否存在
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			teamImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("teamImg");
			String dest = PathUtil.getImgBasePath();
			ImageHolder imageHolder;
			ImgUrl newImgUrlobj=new ImgUrl();
			if(teamImg!=null) {
				try {
					//获取图片
					imageHolder = new ImageHolder(teamImg.getOriginalFilename(),teamImg.getInputStream());
					//复制图片到服务器
					ImageUtil.copyFile(imageHolder, dest, newImgUrl);
					//获取当前图片的扩展名
					fileExtension=ImageUtil.getFileExtension(imageHolder.getImageName());
					//保存对应的扩展名和图片名到 图片对象里，用于更新最新的图片名
					newImgUrlobj.setImgUrlName(newImgUrl+fileExtension);
					//更新图片名
					int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
					if(effectedNum1<1) {
						modelMap.put("success", false);
						modelMap.put("errMsg", "更新imgurl失败");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		//保存变量到团队对象，插入数据库
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
	/** 添加一篇荣誉资质 **/
	@RequestMapping(value = "/addhonor", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> addHonor(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前端传来的标题、日期、文本
		String honorTitle= HttpServletRequestUtil.getString(request, "inputTitle");
		String honorDate= HttpServletRequestUtil.getString(request, "inputDate");
		String honorText= HttpServletRequestUtil.getString(request, "inputText");
		CommonsMultipartFile honorImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Honor honorItem=new Honor();
		//查询当前最新的图片URL名称
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		//URL名称自增1
		String newImgUrl=GetUrlNameUtil.getUrlName(imgUrl);
		//初始化扩展名
		String fileExtension=".jpg";
		if (commonsMultipartResolver.isMultipart(request)) {//判断是否有文件上传
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			honorImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("honorImg");
			//获取绝对路径
			String dest = PathUtil.getImgBasePath();
			ImageHolder imageHolder;
			ImgUrl newImgUrlobj=new ImgUrl();
			if(honorImg!=null) {//判断图片是否获取成功
				try {
					//获取图片
					imageHolder = new ImageHolder(honorImg.getOriginalFilename(),honorImg.getInputStream());
					//复制图片到服务
					ImageUtil.copyFile(imageHolder, dest, newImgUrl);
					//获取图片扩展名
					fileExtension=ImageUtil.getFileExtension(imageHolder.getImageName());
					//设置图片名称和扩展名
					newImgUrlobj.setImgUrlName(newImgUrl+fileExtension);
					//更新图片URL名
					int effectedNum1=imgUrlDao.updateUrlA(newImgUrlobj);
					if(effectedNum1<1) {
						modelMap.put("success", false);
						modelMap.put("errMsg", "更新imgurl失败");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		//设置荣誉对象属性
		honorItem.setAaa502(honorTitle);
		honorItem.setAaa503(PathUtil.filePathInDatabase+newImgUrl+fileExtension);
		honorItem.setAaa504(honorText);
		honorItem.setAaa505(honorDate);
		//插入一条荣誉资质
		int effectedNum=honorDao.insertHonor(honorItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "添加荣誉资质一项失败");
		}
		return modelMap;
	}
}
