package com.sj.spring.controller;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sj.spring.service.UserService;
import com.sj.spring.validator.UserValidator;
import com.sj.spring.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value = "/join")
	public String join(@ModelAttribute("joinUserBean") UserVo joinUserBean, Model model) {
		
		return "user/join";
	}
	
	@PostMapping(value = "/join_pro")
	public String join_pro(@Valid @ModelAttribute("joinUserBean") UserVo joinUserBean, BindingResult result,
						   HttpSession session, HttpServletRequest request) throws MessagingException {
		
		if(result.hasErrors()) {
			System.out.println(result.getAllErrors());
			return "user/join";
		}
		
		String rawEncoding = System.currentTimeMillis() + joinUserBean.getUser_email();
		String hashEncoding = passwordEncoder.encode(rawEncoding);
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        messageHelper.setFrom("sky114z21@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
        messageHelper.setTo(joinUserBean.getUser_email()); // 받는사람 이메일
        messageHelper.setSubject("인증 코드"); // 메일제목은 생략이 가능하다
        messageHelper.setText("<h1>메일인증</h1>" + 
        						"<a href = 'http://localhost:8080/Board_Project/user/valid_email?hashEncoding=" + hashEncoding + "&user_email=" + joinUserBean.getUser_email() + "'>인증하기</a>", true); // 메일 내용
        joinUserBean.setValidation_code(hashEncoding);
    
        mailSender.send(message);
		
        userService.saveValidationCode(joinUserBean);
        
		joinUserBean.setUser_pw(passwordEncoder.encode(joinUserBean.getUser_pw()));
		userService.addUserInfo(joinUserBean);
		return "user/join_success";
		
	}
	
	@GetMapping(value = "/valid_email")
	public String valid_email(@RequestParam("hashEncoding") String hashEncoding, 
							  @RequestParam("user_email") String user_email) {
		String validation_code = userService.getValidationCode(user_email);
		if(validation_code.equals(hashEncoding) == true) {
			userService.changeIsValidation(user_email);
			userService.deleteValidationCode(user_email);
		}
		return "user/valid_success";
	}
	
	@GetMapping(value = "/login")
	public String login(@ModelAttribute("loginUserBean") UserVo loginUserBean, Model model, 
						@RequestParam(value = "fail", defaultValue = "false") boolean fail) {
		
		model.addAttribute("fail", fail);
		
		return "user/login";
		
	}
	
	@PostMapping(value = "/login_pro")
	public String login_pro(@ModelAttribute("loginUserBean") UserVo loginUserBean, 
							HttpServletRequest request, HttpSession session) {
		
		String user_pw = userService.getUserPw(loginUserBean.getUser_id());
		if(user_pw != null) {
			if(passwordEncoder.matches(loginUserBean.getUser_pw(), user_pw)) {
				loginUserBean.setUser_pw(user_pw);
				UserVo tempLoginUserBean = userService.getUserLoginInfo(loginUserBean);
				tempLoginUserBean.setUserLogin(true);
				session.setAttribute("loginUserBean", tempLoginUserBean);
				return "user/login_success";
			}else {
				return "user/login_fail";
			}
		}else {
			return "user/login_fail";
		}
		
	}
	
	@GetMapping(value = "/mypage")
	public String mypage(HttpSession session, HttpServletRequest request) {
		request.setAttribute("user_picture", userService.getUserPicture(((UserVo)session.getAttribute("loginUserBean")).getUser_idx()));
		return "user/mypage";
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "user/logout";
	}
	
	@GetMapping(value = "/not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	@GetMapping(value = "/not_writer")
	public String not_writer() {
		return "user/not_writer";
	}
	
	@GetMapping(value = "/not_validated")
	public String not_validated() {
		return "user/not_validated";
	}
	
	@GetMapping(value = "/not_board_comment_writer")
	public String not_board_comment_writer() {
		return "user/not_board_comment_writer";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
	
}
