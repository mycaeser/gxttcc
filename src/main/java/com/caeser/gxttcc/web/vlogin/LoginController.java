package com.caeser.gxttcc.web.vlogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/vlogin")
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	private String vlogina() {// 显示hr主页
		return "tt/vlogin/login";
	}
}
