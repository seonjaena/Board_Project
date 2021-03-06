package com.sj.spring.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.sj.spring.vo.UserVo;

public interface UserService {

	List<UserVo> getUserList();
	int checkUserIdExist(String user_id);
	int checkUserNickNameExist(String user_nickname);
	int checkUserEmailExist(String user_email);
	String validEmailCode(String user_email);
	void addUserInfo(UserVo joinUserBean);
	String getUserPw(String user_id);
	UserVo getUserLoginInfo(UserVo loginUserBean);
	void saveValidationCode(UserVo joinUserBean);
	String getValidationCode(String user_email);
	void deleteValidationCode(String user_email);
	void changeIsValidation(String user_email);
	String getUserPicture(int user_idx);
	void upload_profile(MultipartFile file, HttpSession session);
	
}
