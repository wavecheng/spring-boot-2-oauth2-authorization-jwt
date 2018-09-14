package com.kristijangeorgiev.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestContoller {

	@GetMapping("/searcher/")
	public String hello() {
		return "ddfadfasf";
	}
	
}
