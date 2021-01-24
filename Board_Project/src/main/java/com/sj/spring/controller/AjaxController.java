package com.sj.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj.spring.service.BoardService;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.UserVo;

@RestController
public class AjaxController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping(value = "/board/write_board_comment")
	public String write_board_comment(@RequestParam(value = "data", required = false) String data, 
									  HttpSession session) {
		int board_idx = Integer.parseInt(data.substring(data.lastIndexOf(",") + 1));
		String comment_text = data.substring(0, data.lastIndexOf(","));
		UserVo loginUserBean = (UserVo)session.getAttribute("loginUserBean");
		CommentVo writeCommentBean = new CommentVo();
		writeCommentBean.setComment_writer_idx(loginUserBean.getUser_idx());
		writeCommentBean.setBoard_idx(board_idx);
		writeCommentBean.setComment_text(comment_text);
		boardService.saveBoardComment(writeCommentBean);
		return String.valueOf(loginUserBean.getUser_idx()) + "," + String.valueOf(boardService.getRecentBoardCommentIdx(loginUserBean.getUser_idx()));
	}
	
	@GetMapping(value = "/board/delete_board_comment")
	public void delete_board_comment(@RequestParam(value = "comment_idx", required = false) int comment_idx) {

		boardService.deleteBoardComment(comment_idx);

	}
	
}
