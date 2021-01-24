package com.sj.spring.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.sj.spring.mapper.BoardMapper;
import com.sj.spring.vo.BoardVo;
import com.sj.spring.vo.CommentVo;
import com.sj.spring.vo.PageVo;
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

}