package com.sj.spring.service;

import java.util.List;

import com.sj.spring.vo.BoardVo;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.PageVo;

public interface BoardService {

	String getBoardTypeName(int board_type_idx);
	void writeBoard(BoardVo writeBoardBean);
	List<BoardVo> getBoardList(int board_type_idx, int page);
	BoardVo getBoardInfo(int board_idx);
	void addBoardViews(int board_idx);
	void deleteBoardInfo(int board_idx);
	PageVo getContentCnt(int board_info_idx, int page);
	void saveBoardComment(CommentVo writeCommentBean);
	int getRecentBoardCommentIdx(int comment_writer_idx);
	List<CommentVo> getBoardCommentList(int board_idx);
	void deleteBoardComment(int comment_idx);
	
}
