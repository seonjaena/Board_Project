package com.sj.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@PropertySource(value = "/WEB-INF/properties/option.properties")
public class WriteController {

	@Value("${upload.path}")
	private String upload_path;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/uploadSummernoteImageFile")
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) {
		
		String file_name = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
		file_name = passwordEncoder.encode(file_name);
		file_name = file_name.replace("/", "a");
		file_name = file_name.replace("\\", "a");
		File targetFile = new File(upload_path + file_name);
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);
		}catch(IOException e) {
			FileUtils.deleteQuietly(targetFile);
			e.printStackTrace();
		}
		return request.getContextPath() + "/img/" + file_name;
	}
	
}
