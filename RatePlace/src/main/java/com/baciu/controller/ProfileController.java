package com.baciu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baciu.entity.User;
import com.baciu.service.PlaceService;
import com.baciu.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlaceService placeService;

	@RequestMapping(value = "profile/{id}")
	public String showProfile(@PathVariable("id") int id, Model model) {
		User user = userService.getById(id);
		if (user == null)
			return "redirect:/main";
		model.addAttribute("places", placeService.getUserPlaces(id));
		model.addAttribute("user", user);
		return "profile";
	}
	
}
