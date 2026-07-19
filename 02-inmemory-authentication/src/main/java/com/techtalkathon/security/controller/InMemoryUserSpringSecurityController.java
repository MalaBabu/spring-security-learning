package com.techtalkathon.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security-inmemory")

public class InMemoryUserSpringSecurityController {
	
	
	@GetMapping("/greeting")
	public String greeting() {
		return "Hello Buddy";
	}
	
	@GetMapping("/reply")
	public String reply() {
		return "Hello";
	}
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Hey Buddy welcome to my application";
	}

}
