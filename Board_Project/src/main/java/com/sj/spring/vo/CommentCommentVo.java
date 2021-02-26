package com.sj.spring.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class CommentCommentVo {

	private int ccomment_idx;
	private int comment_idx;
	private int ccomment_writer_idx;
	private int board_idx;
	private String ccomment_text;
	private String user_nickname;
	private Date ccomment_time;
	
}
