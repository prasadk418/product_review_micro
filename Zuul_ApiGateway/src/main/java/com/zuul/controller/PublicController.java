package com.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PublicController {
	
	
	@RequestMapping("/public")
	public String home() {
		return "Public page";
	}
	
	
}
