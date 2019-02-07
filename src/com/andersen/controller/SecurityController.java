package com.andersen.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import com.andersen.entity.User;
import com.andersen.service.UserService;

@Controller
public class SecurityController {

	@Autowired
	private UserService userService;

	public User getCurrentUser() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String username; 
		
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();

		}
		
		return userService.getCurrentUser(username);

	}

}
