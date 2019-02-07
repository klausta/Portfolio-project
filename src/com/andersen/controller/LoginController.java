package com.andersen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/loginpage")
	public String showLoginForm()  {	
	
		return "login-form";
	}

}
