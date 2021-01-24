package com.sj.spring.vo;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVo {

	private int board_idx;
	private int board_type_idx;
	private int board_writer_idx;
	private String board_title;
	private String board_text;
	private Timestamp board_date;
	private int board_views;
	private int board_recommendation;
	private String user_nickname;
	private String file_name;
	private MultipartFile upload_file;
	
}
