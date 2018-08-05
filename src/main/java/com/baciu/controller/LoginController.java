package com.baciu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.baciu.entity.User;
import com.baciu.service.EmailService;
import com.baciu.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String showLogin(Model model, HttpSession session) {
		if (session.getAttribute("userId") != null)
			return "redirect:main";
		
		model.addAttribute("user", new User());
		return "login";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String validateInput(@ModelAttribute("user") User user, HttpSession session, Model model,
			HttpServletRequest request) {
		
		int userId = userService.logIn(user.getUsername(), user.getPassword());
		
		if (userId == -1) {
			model.addAttribute("loggingMsg", "Nieprawidłowy login lub hasło");
			return "login";
		}
		
		session.setAttribute("userId", userId);
		
		return "redirect:/main";
	}
	
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String addUser(@Valid User user, BindingResult bindingResult,
			@RequestParam("passwordConfirm") String passwordConfirm, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "login";
		}
		
		try {
			userService.addUser(user, passwordConfirm);
		} catch (Exception e) {
			model.addAttribute("registerFailed", e.getMessage());
			return "login";
		}
		
//		try {
//			emailService.sendEmail(user.getEmail(), "Rejestracja konta", "Rejestracja udana. Twój login to : " + user.getUsername());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		model.addAttribute("registerSuccess", "rejestracja udana");
		return "login";
	}
	
	@RequestMapping(value = "logout")
	public String logout(HttpSession session) {
		if (session.getAttribute("userId") != null)
			session.removeAttribute("userId");
		
		return "redirect:login";
	}
}
