package com.caeser.gxttcc.web.cps;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caeser.gxttcc.dao.LocalAuthDao;
import com.caeser.gxttcc.entity.LocalAuth;
import com.caeser.gxttcc.util.HttpServletRequestUtil;


@Controller
@RequestMapping(value = "local", method = { RequestMethod.GET, RequestMethod.POST })
public class CpsLoginController {
	@Autowired
	private LocalAuthDao localAuthDao;
	
	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> logincheck(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 获取输入的帐号
		String userName = HttpServletRequestUtil.getString(request, "userName");
		// 获取输入的密码
		String password = HttpServletRequestUtil.getString(request, "password");
		// 非空校验
		if (userName != null && password != null) {
			// 传入帐号和密码去获取平台帐号信息
			LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(userName, password);
			if (localAuth != null) {
				// 若能取到帐号信息则登录成功
				modelMap.put("success", true);
				// 同时在session里设置用户信息
				request.getSession().setAttribute("user", localAuth.getAac202());
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	/**
	 * 当用户点击登出按钮的时候注销session
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// 将用户session置为空
		request.getSession().setAttribute("user", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
