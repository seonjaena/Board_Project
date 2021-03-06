package com.sj.spring.service;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sj.spring.mapper.UserMapper;
import com.sj.spring.vo.UserVo;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class UserServiceImpl implements UserService {

	@Value("${upload.path}")
	private String upload_path;
	
	@Autowired
	private UserMapper userMapper;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
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

	@Override
	public String getUserPicture(int user_idx) {
		return userMapper.getUserPicture(user_idx);
	}

	@Override
	public void upload_profile(MultipartFile file, HttpSession session) {
		
		String file_name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		file_name = passwordEncoder.encode(file_name);
		file_name = file_name.replace("/", "a");
		file_name = file_name.replace("\\", "b");
		
		try {
			file.transferTo(new File(upload_path + "/" + file_name));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		UserVo userVo = new UserVo();
		userVo.setUser_idx(((UserVo)session.getAttribute("loginUserBean")).getUser_idx());
		userVo.setPicture_name(file_name);
		
		userMapper.upload_profile(userVo);
		
	}

}