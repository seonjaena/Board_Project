package com.sj.spring.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.sj.spring.mapper.BoardMapper;
import com.sj.spring.vo.BoardVo;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.PageVo;
import com.sj.spring.vo.RecommendationVo;
@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardServiceImpl implements BoardService {

	@Value("${page.contentCnt}")
	private int page_contentCnt;
	
	@Value("${page.buttonCnt}")
	private int page_buttonCnt;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public String getBoardTypeName(int board_type_idx) {
		return boardMapper.getBoardTypeName(board_type_idx);
	}

	@Override
	public void writeBoard(BoardVo writeBoardBean) {
		boardMapper.writeBoard(writeBoardBean);
	}

	@Override
	public List<BoardVo> getBoardList(int board_type_idx, int page) {
		int start = (page - 1) * page_contentCnt;
		RowBounds rowBounds = new RowBounds(start, page_contentCnt);
		return boardMapper.getBoardList(board_type_idx, rowBounds);
	}
	
	@Override
	public BoardVo getBoardInfo(int board_idx) {
		return boardMapper.getBoardInfo(board_idx);
	}

	@Override
	public void addBoardViews(int board_idx) {
		boardMapper.addBoardViews(board_idx);
	}

	@Override
	public void deleteBoardInfo(int board_idx) {
		boardMapper.deleteBoardInfo(board_idx);
	}
	
	@Override
	public BoardVo getModifyBoardInfo(BoardVo modifyBoardBean, int board_idx) {
		BoardVo boardVo = boardMapper.getModifyBoardInfo(board_idx);
		modifyBoardBean.setBoard_idx(board_idx);
		modifyBoardBean.setBoard_title(boardVo.getBoard_title());
		modifyBoardBean.setBoard_text(boardVo.getBoard_text());
		modifyBoardBean.setBoard_date(boardVo.getBoard_date());
		modifyBoardBean.setUser_nickname(boardVo.getUser_nickname());
		return modifyBoardBean;
	}
	
	@Override
	public void modifyBoardInfo(BoardVo modifyBoardBean) {
		boardMapper.modifyBoardInfo(modifyBoardBean);
	}

	@Override
	public PageVo getContentCnt(int board_type_idx, int page) {
		int contentCnt = boardMapper.getContentCnt(board_type_idx);
		PageVo pageBean = new PageVo(contentCnt, page, page_contentCnt, page_buttonCnt);
		return pageBean;
	}

	@Override
	public void saveBoardComment(CommentVo writeCommentBean) {
		boardMapper.saveBoardComment(writeCommentBean);
	}
	
	@Override
	public int getRecentBoardCommentIdx(int comment_writer_idx) {
		return boardMapper.getRecentBoardCommentIdx(comment_writer_idx);
	}

	@Override
	public List<CommentVo> getBoardCommentList(int board_idx) {
		return boardMapper.getBoardCommentList(board_idx);
	}

	@Override
	public void deleteBoardComment(int comment_idx) {
		boardMapper.deleteBoardComment(comment_idx);
	}
	
	@Override
	public void modifyBoardComment(CommentVo modifyCommentBean) {
		boardMapper.modifyBoardComment(modifyCommentBean);
	}

	@Override
	public RecommendationVo isUserRecommendedThis(int board_idx, int user_idx) {
		RecommendationVo isRecommended = new RecommendationVo();
		isRecommended.setBoard_idx(board_idx);
		isRecommended.setUser_idx(user_idx);
		return boardMapper.isUserRecommendedThis(isRecommended);
	}

	@Override
	public void recommendThis_new(RecommendationVo recommendationVo) {
		boardMapper.recommendThis_new(recommendationVo);
	}

	@Override
	public void recommendThis_already(RecommendationVo recommendationVo) {
		boardMapper.recommendThis_already(recommendationVo);
	}

	@Override
	public void cancledRecommendThis(RecommendationVo recommendationVo) {
		boardMapper.cancledRecommendThis(recommendationVo);
	}

	@Override
	public void addRecommendation(int board_idx) {
		boardMapper.addRecommendation(board_idx);
	}

	@Override
	public void subRecommendation(int board_idx) {
		boardMapper.subRecommendation(board_idx);
	}

	@Scheduled(cron = "0 30 23 * * *")
	@Override
	public void getHotBoard() {
		boardMapper.downHotBoard();
	}

}