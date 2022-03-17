package com.example.practice.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
	
	@GetMapping("/user/")
	public String home() {
		return "admin welcome to spring security";
	}
	
	@GetMapping("/employee/")
	public String employee() {
		return "employee welcome to spring security";
	}

}
