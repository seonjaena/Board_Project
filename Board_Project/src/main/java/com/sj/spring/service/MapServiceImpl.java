package com.sj.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.spring.mapper.MapMapper;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private MapMapper mapMapper;
	
	@Override
	public String getUserAddr(String user_nickname) {
		return mapMapper.getUserAddr(user_nickname);
	}

}
