package com.sj.spring.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.spring.service.UserService;
import com.sj.spring.vo.UserVo;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	@Inject
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userList(Model model){
		
		List<UserVo> getUserList = userService.getUserList();
		model.addAttribute("list", getUserList);
		return "main";
	}
	
}