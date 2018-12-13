package com.caeser.gxttcc.web.frontend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caeser.gxttcc.dao.AboutUsDao;
import com.caeser.gxttcc.dao.CultureDao;
import com.caeser.gxttcc.dao.HonorDao;
import com.caeser.gxttcc.dao.TeamDao;
import com.caeser.gxttcc.entity.AboutUs;
import com.caeser.gxttcc.entity.Culture;
import com.caeser.gxttcc.entity.Honor;
import com.caeser.gxttcc.entity.Team;

@Controller
@RequestMapping("/companygx")
public class AboutPageController {
	@Autowired
	private AboutUsDao aboutUsDao;
	@Autowired
	private CultureDao cultureDao;
	@Autowired
	private TeamDao teamDao;
	@Autowired
	private HonorDao honorDao;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	private String index(){
		return "tt/about";
	}
	
	@RequestMapping(value="/getaboutall",method=RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getAboutAll(HttpServletRequest request){
		Map<String,Object> modelMap=new HashMap<String,Object>();
		AboutUs aboutUs=aboutUsDao.queryAboutUs();
		Culture culture=cultureDao.queryCulture();
		List<Team> teamList=teamDao.queryTeam();
		List<Honor> honorList=honorDao.queryHonorAll();
		if(aboutUs==null||culture==null||teamList==null||honorList==null) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "getaboutall error");
		}else {
			modelMap.put("aboutUs", aboutUs);
			modelMap.put("culture", culture);
			modelMap.put("teamList", teamList);
			modelMap.put("honorList", honorList);
		}
		return modelMap;
	}
}
