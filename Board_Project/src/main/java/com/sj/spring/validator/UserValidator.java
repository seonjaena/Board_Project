package com.sj.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sj.spring.vo.UserVo;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		UserVo userBean = (UserVo)target;
		String beanName = errors.getObjectName();
		
		if(beanName.equals("joinUserBean")) {
			if(userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			if(userBean.isUserIdExist() == false) {
				errors.rejectValue("user_id", "DontCheckUserIdExist");
			}
			if(userBean.isUserNicknameExist() == false) {
				errors.rejectValue("user_nickname", "DontCheckUserNickNameExist");
			}
			if(userBean.isUserEmailExist() == false) {
				errors.rejectValue("user_email", "DontCheckUserEmailExist");
			}
		}
		
	}

}