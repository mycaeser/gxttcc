package com.caeser.gxttcc.web.cps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cps")
public class HrController {
	
	@RequestMapping(value = "/hradmin", method = RequestMethod.GET)
	private String admin() {// 显示hr主页
		return "tt/cps/chr";
	}
	@RequestMapping(value = "/addhrarticleview", method = RequestMethod.GET)
	private String addHrArticleView() {// 显示添加一篇hr文章
		return "tt/cps/newscompanypart/humanresourcearticle";
	}
}
