package com.sj.spring.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sj.spring.vo.BoardVo;
import com.sj.spring.vo.CommentVo;

public interface BoardMapper {

	String getBoardTypeName(int board_type_idx);
	void writeBoard(BoardVo writeBoardBean);
	List<BoardVo> getBoardList(int board_type_idx, RowBounds rowBounds);
	BoardVo getBoardInfo(int board_idx);
	void addBoardViews(int board_idx);
	void deleteBoardInfo(int board_idx);
	int getContentCnt(int board_type_idx);
	void saveBoardComment(CommentVo writeCommentBean);
	int getRecentBoardCommentIdx(int comment_writer_idx);
	List<CommentVo> getBoardCommentList(int board_idx);
	void deleteBoardComment(int comment_idx);
	
}
