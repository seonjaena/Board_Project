package com.sj.spring.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sj.spring.mapper.UserMapper;
import com.sj.spring.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<UserVo> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public int checkUserIdExist(String user_id) {
		return userMapper.checkUserIdExist(user_id);
	}

	@Override
	public int checkUserNickNameExist(String user_nickname) {
		return userMapper.checkUserNickNameExist(user_nickname);
	}
	
	@Override
	public int checkUserEmailExist(String user_email) {
		return userMapper.checkUserEmailExist(user_email);
	}

	@Override
	public String validEmailCode(String user_email) {
		return userMapper.validEmailCode(user_email);
	}

	@Override
	public void addUserInfo(UserVo joinUserBean) {
		userMapper.addUserInfo(joinUserBean);
	}

	@Override
	public String getUserPw(String user_id) {
		return userMapper.getUserPw(user_id);
	}

	@Override
	public UserVo getUserLoginInfo(UserVo loginUserBean) {
		return userMapper.getUserLoginInfo(loginUserBean);
	}

	@Override
	public void saveValidationCode(UserVo joinUserBean) {
		userMapper.saveValidationCode(joinUserBean);
	}

	@Override
	public String getValidationCode(String user_email) {
		return userMapper.getValidationCode(user_email);
	}

	@Override
	public void deleteValidationCode(String user_email) {
		userMapper.deleteValidationCode(user_email);
	}

	@Override
	public void changeIsValidation(String user_email) {
		userMapper.changeIsValidation(user_email);
	}

}