package com.caeser.gxttcc.web.cps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/addprojectsarticle")
public class AddProjectsController {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String admin() {// 显示主页
		return "tt/cps/addarticle";
	}
}
