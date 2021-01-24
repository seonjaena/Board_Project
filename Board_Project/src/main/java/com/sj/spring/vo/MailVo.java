package com.sj.spring.vo;

import java.util.List;

import lombok.Data;
@Data
public class MailVo {
	private String from;
	private List<String> to;
	private String subject;
	private String text;
}
