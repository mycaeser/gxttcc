package com.caeser.gxttcc.service.impl;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.transaction.annotation.Transactional;
//import com.caeser.gxttcc.dao.ImgUrlDao;
//import com.caeser.gxttcc.dao.TeamDao;
//import com.caeser.gxttcc.entity.ImgUrl;
//import com.caeser.gxttcc.util.GetUrlNameUtil;

import org.springframework.stereotype.Service;
import com.caeser.gxttcc.entity.Team;
import com.caeser.gxttcc.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService{
//	@Autowired
//	private ImgUrlDao imgUrlDao;
//	@Autowired
//	private TeamDao teamDao;
	
	@Override
	public boolean insertTeam(Team team) {
		boolean result=false;
//		ImgUrl imgUrl=imgUrlDao.queryUrl();
//		String newImgUrl=GetUrlNameUtil.getUrlName(imgUrl);
//		team.setAaa403(newImgUrl);
//		int effectedNum=teamDao.insertTeam(team);
//		if(effectedNum>0) {
//			ImgUrl testImgUrl=new ImgUrl();
//			testImgUrl.setImgUrlName(newImgUrl+".jpg");
//			int effectedNum1=imgUrlDao.updateUrlA(testImgUrl);
//			if(effectedNum1>0) {
//				//success
//			}
//			result=true;
//		}else {
//			result=false;
//		}
		return result;
	}
}
