package com.nut4disney.spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello World Controller
 * 
 * @author nut4disney
 */
@RestController
public class HellowWorldController {
	@GetMapping()
	public String index() {
		return "Hello, from the Hello World Controller!";
	}
}
