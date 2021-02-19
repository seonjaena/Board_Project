package com.sj.spring.mapper;

import java.util.List;

import com.sj.spring.vo.UserVo;

public interface UserMapper {

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
	
}
