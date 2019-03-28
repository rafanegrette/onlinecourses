package com.rafanegrette.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

	@GetMapping
	String hello() {
		return "Hello";
	}
}
