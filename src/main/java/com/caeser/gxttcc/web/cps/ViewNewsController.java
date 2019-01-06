package com.caeser.gxttcc.web.cps;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cps")
public class ViewNewsController {

	@RequestMapping(value = "/viewparta", method = RequestMethod.GET)
	private String viewPartA() {// 公司动态
		return "tt/cps/cnews";
	}

	@RequestMapping(value = "/cprojects", method = RequestMethod.GET)
	private String viewProjects() {// 公司动态
		return "tt/cps/cprojects";
	}
}
