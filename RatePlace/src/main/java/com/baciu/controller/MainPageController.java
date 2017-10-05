package com.baciu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baciu.dao.PlaceDAO;
import com.baciu.entity.Place;

@Controller
public class MainPageController {
	
	@Autowired
	private PlaceDAO placeService;

	@RequestMapping(value = "main")
	public String showMainPage(Model model, HttpSession session) {
		List<Place> places = placeService.getAll();
		model.addAttribute("topPlaces", places);

		return "index";
	}
	
}
