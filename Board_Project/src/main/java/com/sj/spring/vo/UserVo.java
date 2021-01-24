package com.sj.spring.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {

	private int user_idx;
	
	@Size(min = 2, max = 5)
	@Pattern(regexp = "[가-힣]*")
	private String user_name;

	@Email
	@NotBlank
	private String user_email;
	
	@Size(min = 6, max = 15)
	@Pattern(regexp = "[a-zA-Z0-9가-힣]*")
	private String user_id;
	
	@Size(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$")
	private String user_pw;
	
	@Size(min = 8, max = 20)
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,20}$")
	private String user_pw2;
	
	@NotBlank
	private String zip_code;
	
	@NotBlank
	private String main_addr;
	
	@NotBlank
	private String reference_addr;
	
	@NotBlank
	private String detail_addr;
	
	@Size(min = 2, max = 15)
	@Pattern(regexp = "[a-zA-Z0-9가-힣]*")
	private String user_nickname;
	
	private String validation_code;
	
	private boolean userIdExist;
	private boolean userNicknameExist;
	private boolean userEmailExist;
	private boolean userLogin;
	private int is_validation;
	
	public UserVo() {
		this.userIdExist = false;
		this.userNicknameExist = false;
		this.userLogin = false;
	}
	
}
