package com.sj.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj.spring.service.BoardService;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.RecommendationVo;
import com.sj.spring.vo.UserVo;

@RestController
public class AjaxController {

	@Autowired
	private BoardService boardService;
	
	@PostMapping(value = "/board/write_board_comment", produces = "application/text;charset=utf8")
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
		return ((UserVo)session.getAttribute("loginUserBean")).getUser_nickname() + "," + String.valueOf(loginUserBean.getUser_idx()) + "," + String.valueOf(boardService.getRecentBoardCommentIdx(loginUserBean.getUser_idx()));
	}
	
	@GetMapping(value = "/board/delete_board_comment")
	public void delete_board_comment(@RequestParam(value = "comment_idx", required = false) int comment_idx) {

		boardService.deleteBoardComment(comment_idx);

	}
	
	@PostMapping(value = "/board/modify_board_comment")
	public void modify_board_comment(@RequestParam(value = "data") String data, HttpSession session) {
		int comment_idx = Integer.parseInt(data.substring(data.lastIndexOf(",") + 1));
		String comment_text = data.substring(0, data.lastIndexOf(","));
		CommentVo modifyCommentBean = new CommentVo();
		modifyCommentBean.setComment_idx(comment_idx);
		modifyCommentBean.setComment_text(comment_text);
		boardService.modifyBoardComment(modifyCommentBean);
	}
	
	@GetMapping(value = "/board/toggleRecommend")
	public String toggleRecommend(@RequestParam(value = "state") int state, HttpSession session, 
								  @RequestParam(value = "board_idx") int board_idx) {
		
		RecommendationVo recommendationVo = new RecommendationVo();
		recommendationVo.setBoard_idx(board_idx);
		recommendationVo.setUser_idx(((UserVo)session.getAttribute("loginUserBean")).getUser_idx());
		if(state == 0) {
			if(boardService.isUserRecommendedThis(board_idx, ((UserVo)session.getAttribute("loginUserBean")).getUser_idx()) != null) {
				boardService.recommendThis_already(recommendationVo);
			}else {
				boardService.recommendThis_new(recommendationVo);
			}
			boardService.addRecommendation(board_idx);
			return "1";
		}else if(state == 1) {
			boardService.cancledRecommendThis(recommendationVo);
			boardService.subRecommendation(board_idx);
			return "0";
		}else {
			return "-100";
		}
	}
}