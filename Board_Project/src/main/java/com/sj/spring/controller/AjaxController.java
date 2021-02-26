package com.sj.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sj.spring.service.BoardService;
import com.sj.spring.vo.CommentCommentVo;
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
	
	@PostMapping(value = "/board/write_comment_comment", produces = "application/text;charset=utf8")
	public String write_comment_comment(@RequestParam(value = "data", required = false) String data, 
										@RequestParam(value = "board_idx", required = false) String board_idx, 
										HttpSession session) {
		int comment_idx = Integer.parseInt(data.substring(data.lastIndexOf(",") + 1));
		String comment_comment_text = data.substring(0, data.lastIndexOf(","));
		UserVo loginUserBean = (UserVo)session.getAttribute("loginUserBean");
		CommentCommentVo commentCommentVo = new CommentCommentVo();
		commentCommentVo.setCcomment_writer_idx(loginUserBean.getUser_idx());
		commentCommentVo.setComment_idx(comment_idx);
		commentCommentVo.setCcomment_text(comment_comment_text);
		commentCommentVo.setBoard_idx(Integer.parseInt(board_idx));
		boardService.saveCommentComment(commentCommentVo);
		return ((UserVo)session.getAttribute("loginUserBean")).getUser_nickname() + "," + String.valueOf(loginUserBean.getUser_idx()) + "," + String.valueOf(boardService.getRecentCommentCommentIdx(loginUserBean.getUser_idx()));
	}
	
	@GetMapping(value = "/board/delete_comment_comment")
	public void delete_comment_comment(@RequestParam(value = "ccomment_idx") String ccomment_idx, HttpSession session) {
		UserVo loginUserBean = (UserVo)session.getAttribute("loginUserBean");
		CommentCommentVo commentCommentVo = new CommentCommentVo();
		commentCommentVo.setCcomment_idx(Integer.parseInt(ccomment_idx));
		commentCommentVo.setCcomment_writer_idx(loginUserBean.getUser_idx());
		boardService.deleteCommentComment(commentCommentVo);
	}
	
	@PostMapping(value = "/user/upload_profile")
	public void upload_profile(@RequestParam(value = "data") MultipartFile data) {
		System.out.println(data.getOriginalFilename());
	}
	
}