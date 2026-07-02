package com.techtalkathon.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security-basic")

public class DemoRestController {
	
	
	@GetMapping("/greeting")
	public String greeting() {
		return "Hello Buddy";
	}

}
