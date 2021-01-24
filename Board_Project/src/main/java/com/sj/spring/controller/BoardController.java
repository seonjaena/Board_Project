package com.sj.spring.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.spring.service.BoardService;
import com.sj.spring.vo.BoardVo;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.PageVo;
import com.sj.spring.vo.UserVo;

@Controller
@RequestMapping(value = "/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = "/main")
	public String main(@RequestParam(value = "board_type_idx", defaultValue = "1") int board_type_idx, 
					   @RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		String board_type_name = boardService.getBoardTypeName(board_type_idx);
		model.addAttribute("board_type_name", board_type_name);
		model.addAttribute("board_type_idx", board_type_idx);
		List<BoardVo> boardList = boardService.getBoardList(board_type_idx, page);
		model.addAttribute("boardList", boardList);
		PageVo pageBean = boardService.getContentCnt(board_type_idx, page);
		model.addAttribute("pageBean", pageBean);
		return "board/main";
	}
	
	@GetMapping(value = "/write")
	public String write(@RequestParam(value = "board_type_idx") int board_type_idx, 
						@ModelAttribute("writeBoardBean") BoardVo writeBoardBean) {
		writeBoardBean.setBoard_type_idx(board_type_idx);
		return "board/write";
	}
	
	@PostMapping(value = "/write_pro")
	public String write_pro(@Valid @ModelAttribute("writeBoardBean") BoardVo writeBoardBean, 
							BindingResult result, HttpSession session) {
		
		if(result.hasErrors()) {
			return "board/write";
		}
		writeBoardBean.setBoard_writer_idx(((UserVo)session.getAttribute("loginUserBean")).getUser_idx());
		boardService.writeBoard(writeBoardBean);
		
		return "board/write_success";
		
	}
	
	@GetMapping(value = "/read")
	public String read(Model model, @RequestParam("board_idx") int board_idx,
					   @RequestParam("board_type_idx") int board_type_idx, HttpSession session) {
		BoardVo readBoardBean = boardService.getBoardInfo(board_idx);
		if(readBoardBean.getBoard_writer_idx() != ((UserVo)session.getAttribute("loginUserBean")).getUser_idx()) {
			boardService.addBoardViews(board_idx);
		}
		List<CommentVo> commentList = boardService.getBoardCommentList(board_idx);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("readBoardBean", readBoardBean);
		model.addAttribute("board_type_idx", board_type_idx);
		model.addAttribute("commentList", commentList);
		return "board/read";
	}
	
	@GetMapping(value = "/delete")
	public String delete(@RequestParam("board_type_idx") int board_type_idx, 
						 @RequestParam("board_idx") int board_idx, Model model) {

		boardService.deleteBoardInfo(board_idx);
		model.addAttribute("board_type_idx", board_type_idx);
		return "board/delete";
	}
	
}