package com.sj.spring.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sj.spring.service.MapService;
import com.sj.spring.service.UserService;
import com.sj.spring.vo.UserVo;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MapService mapService;
	
	@Inject
	PasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userList(Model model){
		
		List<UserVo> getUserList = userService.getUserList();
		model.addAttribute("list", getUserList);
		return "main";
	}
	
	@GetMapping(value = "/main")
	public String main() {
		return "main";
	}
	
	@GetMapping(value = "/bcd")
	public String bcd(HttpSession session, Model model) {
		String user_nickname = ((UserVo)session.getAttribute("loginUserBean")).getUser_nickname();
		String main_addr = mapService.getUserAddr(user_nickname);
		model.addAttribute("main_addr", main_addr);
		return "bcd";
	}

}