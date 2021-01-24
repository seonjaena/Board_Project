package com.sj.spring.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.sj.spring.vo.MailVo;

@Controller
public class MailController {
	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping(value = "/sendMailForm")
	public String sendMailForm(@ModelAttribute("mailBean") MailVo mailBean) {
		return "send_mail";
	}
	
	@PostMapping(value = "/sendMail")
	public String sendMail(@ModelAttribute("mailBean") MailVo mailBean) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
        for (String string : mailBean.getTo()) {
        	messageHelper.setFrom("sky114z21@gmail.com"); // 보내는사람 생략하거나 하면 정상작동을 안함
        	messageHelper.setTo(string); // 받는사람 이메일
        	messageHelper.setSubject("인증 코드"); // 메일제목은 생략이 가능하다
        	messageHelper.setText(mailBean.getText()); // 메일 내용
    
        	mailSender.send(message);   
       }

		return "main";
	}
	
}
