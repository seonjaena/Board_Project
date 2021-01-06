package com.sj.spring.vo;

import java.sql.Date;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserVo {

	private int user_idx;
	
	@Size(min = 2, max = 5)
	@Pattern(regexp = "[가-힣]*")
	private String user_name;
	
	private Date user_birth;

	private String user_email;
	
	@Size(min = 6, max = 15)
	@Pattern(regexp = "[a-zA-Z0-9가-힣]*")
	private String user_id;
	
	private String user_pw;
	private String user_pw2;
	private String user_nickname;
	
}
