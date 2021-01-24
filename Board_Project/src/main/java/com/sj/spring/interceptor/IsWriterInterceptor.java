package com.sj.spring.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sj.spring.service.BoardService;
import com.sj.spring.vo.UserVo;

public class IsWriterInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BoardService boardService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		try {
			HttpSession session = request.getSession();
			
			int user_idx = ((UserVo)session.getAttribute("loginUserBean")).getUser_idx();
			int board_idx = Integer.parseInt(request.getParameter("board_idx"));
			int board_writer_idx = boardService.getBoardInfo(board_idx).getBoard_writer_idx();
			if(user_idx != board_writer_idx) {
				String context_path = request.getContextPath();
				response.sendRedirect(context_path + "/user/not_writer");
				return false;
			}
			
			return true;
			
		}catch(Exception e) {
			String context_path = request.getContextPath();
			response.sendRedirect(context_path + "/user/not_writer");
			return false;
		}
		
	}
	
}