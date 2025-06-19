package com.application.security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.application.security.model.UserInfoDTO;
import com.application.security.service.UserInfoService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("")
public class Controller {

	@Autowired
	UserInfoService userInfoService;

	@GetMapping("/path")
	public Object getMethodName(HttpServletRequest request) {
		System.out.println("logged" + request.getAttribute("_csrf"));
		return request.getAttribute("_csrf");
	}

	@PostMapping("/add/user")
	public ResponseEntity<String> addUser(@RequestBody UserInfoDTO userInfoDTO) {
		try {
			String response = userInfoService.addUser(userInfoDTO);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
