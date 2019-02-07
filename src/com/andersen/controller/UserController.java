package com.andersen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.andersen.dao.UserDAO;
import com.andersen.entity.Transaction;
import com.andersen.entity.User;
import com.andersen.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private UserService userService;
	
	@RequestMapping("/list")
	public String listUsers(Model model) {

		List<User> userList = userService.getUsers();

		model.addAttribute("users", userList);

		return "/user/list-users";
	}

	@RequestMapping("/adduserform")
	public String addUserForm(Model model) {

		model.addAttribute("user", new User());

		return "/user/adduser-form";
	}

	//TO-DO: add exception handling
	@RequestMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") User user) {
		
		userService.addUser(user);

		return "redirect:/user/list";
	}
}
