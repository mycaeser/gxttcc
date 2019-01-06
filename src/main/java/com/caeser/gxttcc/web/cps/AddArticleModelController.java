package com.caeser.gxttcc.web.cps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addarticlemodel")
public class AddArticleModelController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String admin() {// 显示主页
		return "tt/cps/addarticle";
	}
	@RequestMapping(value = "/addteam", method = RequestMethod.GET)
	private String addTeam() {// 显示主页
		return "tt/cps/addteamarticle";
	}
}
