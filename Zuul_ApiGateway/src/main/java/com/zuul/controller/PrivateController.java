package com.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrivateController {
	
		
	@RequestMapping("/private")
	public String details() {
		return "private page";
	}
	

}
