package com.caeser.gxttcc.interceptor;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CpsLoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从session中取出用户信息来
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			// 若用户信息不为空则将session里的用户信息转换成PersonInfo实体类对象
			String userName = (String) userObj;
			// 做空值判断，确保userId不为空并且该帐号的可用状态为1，并且用户类型为店家
			if (userName!=null)
				// 若通过验证则返回true,拦截器返回true之后，用户接下来的操作得以正常执行
				return true;
		}
		// 若不满足登录验证，则直接跳转到帐号登录页面
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<script>");
		out.println("window.open ('" + request.getContextPath() + "/vlogin/login','_self')");
		out.println("</script>");
		out.println("</html>");
		return false;
	}
}
