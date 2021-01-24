package com.sj.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sj.spring.vo.UserVo;

public class UserInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		try {
			HttpSession session = request.getSession(false);
			
			UserVo loginUserBean = (UserVo)session.getAttribute("loginUserBean");
			
			if(loginUserBean == null) {
				String context_path = request.getContextPath();
				response.sendRedirect(context_path + "/user/not_login");
				return false;
			}
			
			return true;
			
		}catch(Exception e) {
			String context_path = request.getContextPath();
			response.sendRedirect(context_path + "/user/not_login");
			return false;
		}
		
	}
	
}
