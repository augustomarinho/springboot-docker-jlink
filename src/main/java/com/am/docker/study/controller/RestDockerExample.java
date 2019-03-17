package com.am.docker.study.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestDockerExample {

	@RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}
}