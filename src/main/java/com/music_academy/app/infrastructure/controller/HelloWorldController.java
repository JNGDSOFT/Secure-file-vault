package com.music_academy.app.infrastructure.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/helloworld")
@RestController
public class HelloWorldController {

	@GetMapping
	public String helloWorld() {
		return "Hello world!";
	}

	@GetMapping("/auth")
	public String helloWorldAuthenticated() {
		return "Hello world! ... authenticated!";
	}

}