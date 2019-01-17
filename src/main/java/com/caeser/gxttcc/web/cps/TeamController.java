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

import com.caeser.gxttcc.dao.TeamDao;
import com.caeser.gxttcc.dto.ImageHolder;
import com.caeser.gxttcc.entity.Team;
import com.caeser.gxttcc.util.HttpServletRequestUtil;
import com.caeser.gxttcc.util.ImageUtil;
import com.caeser.gxttcc.util.PathUtil;


@Controller
@RequestMapping("/cps")
public class TeamController {
	@Autowired
	private TeamDao teamDao;
	
	@RequestMapping(value = "/getteambyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getTeamById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int teamId = HttpServletRequestUtil.getInt(request, "id");
		Team teamItem=teamDao.queryTeamById(teamId);
		if(teamItem!=null) {
			modelMap.put("teamItem", teamItem);
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "获取团队一项失败");
		}
		return modelMap;
	}
	@RequestMapping(value = "/deleteteambyid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> deleteTeamById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int teamId = HttpServletRequestUtil.getInt(request, "id");
		
		Team deleteTeamObj=new Team();
		deleteTeamObj=teamDao.queryTeamById(teamId);
		if(deleteTeamObj!=null) {
			String teamImgName=deleteTeamObj.getAaa403();
			teamImgName=teamImgName.substring(PathUtil.filePathInDatabase.length(),teamImgName.length());
			String dest = PathUtil.getImgBasePath();
			String relativeAddr = dest + teamImgName;
			ImageUtil.deleteFileOrPath(relativeAddr);
		}
		int effectedNum=teamDao.deleteTeamById(teamId);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "删除团队一项失败");
		}
		return modelMap;
	}
	@RequestMapping(value = "/updateteambyid", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> updateTeamById(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		int teamId = HttpServletRequestUtil.getInt(request, "teamId");
		String teamTitle= HttpServletRequestUtil.getString(request, "teamTitle");
		String teamDate= HttpServletRequestUtil.getString(request, "teamDate");
		CommonsMultipartFile teamImg=null;
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		Team teamItem=new Team();
		
		if (commonsMultipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
			teamImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("teamFile");
		}
		if(teamImg!=null) {
			try {
				ImageHolder imageHolder=new ImageHolder(teamImg.getOriginalFilename(),teamImg.getInputStream());
				String dest = PathUtil.getImgBasePath();
				String tmpName=teamDao.queryTeamById(teamId).getAaa403();
				tmpName=tmpName.substring(PathUtil.filePathInDatabase.length(),tmpName.lastIndexOf("."));
				ImageUtil.generateNormalImg(imageHolder, dest,tmpName);
			} catch (IOException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.getMessage());
			}
		}
		teamItem.setAaa401(teamId);
		teamItem.setAaa402(teamTitle);
		teamItem.setAaa404(teamDate);
		int effectedNum=teamDao.updateTeam(teamItem);
		if(effectedNum<1) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "更新团队一项失败");
		}
		return modelMap;
	}
}
