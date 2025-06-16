package com.application.security.controller;

import org.springframework.web.bind.annotation.RestController;

import com.application.security.model.ModelDTO;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("")
public class Controller {
	
	@GetMapping("/path")
	public Object getMethodName(HttpServletRequest request) {
		System.out.println("logged"+request.getAttribute("_csrf"));
		return request.getAttribute("_csrf");
	}
	
	@PostMapping("/")
	public String postMethodName(@RequestBody ModelDTO model) {
		return "HI";
	}
	
}
