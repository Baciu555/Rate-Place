package com.baciu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.UsernameExistsException;
import com.baciu.service.PlaceService;
import com.baciu.service.UserService;

@Controller
public class AccountController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlaceService placeService;

	@RequestMapping(value = "account")
	public String showAccount(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		int userId = (int) session.getAttribute("userId");
		
		model.addAttribute("user", userService.getById(userId));
		model.addAttribute("places", placeService.getUserPlaces(userId));
		
		return "account";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public String showEdit(HttpSession session, Model model) {
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		int userId = (int) session.getAttribute("userId");
		User user = userService.getById(userId);
		model.addAttribute("user", user);
		return "edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String showEdit(@Valid User user, BindingResult bindingResult,
			RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		int userId = (int) session.getAttribute("userId");
		User loggedUser = userService.getById(userId);
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(bindingResult.getFieldError().getField(), bindingResult.getFieldError().getDefaultMessage());
			return "redirect:/edit";
		}
		
		loggedUser.setUsername(user.getUsername());
		loggedUser.setEmail(user.getEmail());
		
		try {
			userService.update(loggedUser, user);
		} catch (UsernameExistsException e) {
			model.addAttribute("username", e.getMessage());
			return "edit";
		} catch (EmailExistsException e) {
			model.addAttribute("email", e.getMessage());
			return "edit";
		}
		
		model.addAttribute("updateMsg", "Zmieniono dane");
		return "edit";
	}
	
	@RequestMapping(value = "changeAvatar", method = RequestMethod.GET)
	public String showChangeAvatar(HttpSession session, Model model) {
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		return "changeAvatar";
	}
	
	@RequestMapping(value = "changeAvatar", method = RequestMethod.POST)
	public String postChangeAvatar(@RequestParam("file") MultipartFile file, HttpSession session, Model model) {
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		int userId = (int) session.getAttribute("userId");
		User user = userService.getById(userId);
		user.setAvatarPath(file.getOriginalFilename());
		
		try {
			userService.uploadAvatar(file);
			userService.updateAvatar(user);
		} catch (Exception e) {
			model.addAttribute("message", e.getMessage());
			return "changeAvatar";
		}
		
		model.addAttribute("message", "Zmieniono avatar");
		
		return "changeAvatar";
	}
	
	@RequestMapping(value = "avatar/{imageName:.+}")
	@ResponseBody
	public byte[] getAvatar(@PathVariable(value = "imageName") String imageName) throws IOException {		
		Path uploadedFolder = Paths.get("uploads/avatars");
	    File serverFile = new File(uploadedFolder + "/"+ imageName);

	    return Files.readAllBytes(serverFile.toPath());
	}
}
