package com.sj.spring.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.sj.spring.vo.BoardVo;
import com.sj.spring.vo.CommentCommentVo;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.RecommendationVo;

public interface BoardMapper {

	String getBoardTypeName(int board_type_idx);
	void writeBoard(BoardVo writeBoardBean);
	List<BoardVo> getBoardList(int board_type_idx, RowBounds rowBounds);
	BoardVo getBoardInfo(int board_idx);
	void addBoardViews(int board_idx);
	void deleteBoardInfo(int board_idx);
	BoardVo getModifyBoardInfo(int board_idx);
	void modifyBoardInfo(BoardVo modifyBoardBean);
	int getContentCnt(int board_type_idx);
	void saveBoardComment(CommentVo writeCommentBean);
	int getRecentBoardCommentIdx(int comment_writer_idx);
	List<CommentVo> getBoardCommentList(int board_idx);
	void deleteBoardComment(int comment_idx);
	void modifyBoardComment(CommentVo modifyCommentBean);
	RecommendationVo isUserRecommendedThis(RecommendationVo isRecommended);
	void recommendThis_new(RecommendationVo recommendationVo);
	void recommendThis_already(RecommendationVo recommendationVo);
	void cancledRecommendThis(RecommendationVo recommendationVo);
	void addRecommendation(int board_idx);
	void subRecommendation(int board_idx);
	void downHotBoard();
	void saveCommentComment(CommentCommentVo commentCommentVo);
	int getRecentCommentCommentIdx(int ccomment_writer_idx);
	List<CommentCommentVo> getCommentCommentList(int comment_idx);
	void deleteCommentComment(CommentCommentVo commentCommentVo);
	
}
