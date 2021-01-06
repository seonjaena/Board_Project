package com.sj.spring.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sj.spring.mapper.UserMapper;
import com.sj.spring.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Inject
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<UserVo> getUserList() {
		return userMapper.getUserList();
	}

}