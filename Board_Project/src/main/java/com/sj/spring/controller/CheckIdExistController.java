package com.sj.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sj.spring.service.UserService;

@RestController
public class CheckIdExistController {

	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/user/checkUserIdExist")
	public String checkUserIdExist(@RequestParam("user_id") String user_id) {
		int user_num = userService.checkUserIdExist(user_id);
		if(user_num == 0) {
			return "false";
		}else {
			return "true";
		}
	}
	
	@GetMapping(value = "/user/checkUserNickNameExist")
	public String checkUserNickNameExist(@RequestParam("user_nickname") String user_nickname) {
		int user_num = userService.checkUserNickNameExist(user_nickname);
		if(user_num == 0) {
			return "false";
		}else {
			return "true";
		}
	}
	
	@GetMapping(value = "/user/checkUserEmailExist")
	public String checkUserEmailExist(@RequestParam("user_email") String user_email) {
		int user_num = userService.checkUserEmailExist(user_email);
		if(user_num == 0) {
			return "false";
		}else {
			return "true";
		}
	}
	
}