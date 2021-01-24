package com.sj.spring.vo;

import java.sql.Date;

import lombok.Data;
@Data
public class CommentVo {

	private int comment_idx;
	private int board_idx;
	private int comment_writer_idx;
	private String comment_text;
	private String user_nickname;
	private Date comment_time;
	
}
