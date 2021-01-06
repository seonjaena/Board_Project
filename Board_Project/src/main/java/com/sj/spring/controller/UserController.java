package com.sj.spring.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sj.spring.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping(value = "/join")
	public String join(@ModelAttribute("joinUserBean") UserVo joinUserBean) {
		return "user/join";
	}
	
	@PostMapping(value = "/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserVo joinUserBean, BindingResult result) {
		if(result.hasErrors()) {
			return "user/join";
		}
		return "main";
	}
	
}
