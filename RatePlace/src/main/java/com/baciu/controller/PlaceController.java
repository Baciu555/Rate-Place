package com.baciu.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

import com.baciu.entity.Opinion;
import com.baciu.entity.Place;
import com.baciu.entity.User;
import com.baciu.exception.FileUploadException;
import com.baciu.exception.PlaceExistsException;
import com.baciu.service.OpinionService;
import com.baciu.service.PlaceService;
import com.baciu.service.TypeService;
import com.baciu.service.UserService;

@Controller
public class PlaceController {
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private OpinionService opinionService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "place", method = RequestMethod.GET)
	public String redirectToMainPage() {
		return "redirect:main";
	}

	@RequestMapping(value = "place/{placeId}", method = RequestMethod.GET)
	public String showPlace(@PathVariable int placeId, Model model) {
		
		Place place = placeService.getPlace(placeId);
		if (place == null)
			return "redirect:/main";
		
		List<Opinion> opinions = opinionService.getByPlaceId(placeId);
		
		model.addAttribute("placeId", placeId);
		model.addAttribute("place", place);
		model.addAttribute("opinions", opinions);
		model.addAttribute("opinion", new Opinion());
		
		return "place";
	}
	
	@RequestMapping(value = "/place/{placeId}", method = RequestMethod.POST)
	public String ratePlace(@Valid Opinion opinion, BindingResult bindingResult,
			@PathVariable("placeId") int placeId, HttpSession session,
			RedirectAttributes redirectAttributes) {
		
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute(bindingResult.getFieldError().getField(), bindingResult.getFieldError().getDefaultMessage());
			return "redirect:/place/" + placeId;
		}
		
		int userId = (int) session.getAttribute("userId");
		User user = userService.getById(userId);
		Place place = placeService.getPlace(placeId);
		opinion.setUser(user);
		opinion.setPlace(place);
		opinionService.addOpinion(opinion);
		
		System.out.println("dodano");
		redirectAttributes.addFlashAttribute("successMsg", "Dodano opinie");
		return "redirect:/place/" + placeId;
	}
	
	@RequestMapping(value = "addPlace", method = RequestMethod.GET)
	public String addPlace(Model model, HttpSession session) {
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		model.addAttribute("place", new Place());
		model.addAttribute("types", typeService.getAll());
		
		return "addPlace";
	}
	
	@RequestMapping(value = "addPlace", method = RequestMethod.POST)
	public String addPlace(@Valid Place place, BindingResult bindingResult,
			@RequestParam("file") MultipartFile file, Model model, HttpSession session) {
		
		if (session.getAttribute("userId") == null)
			return "redirect:main";
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("types", typeService.getAll());
			return "addPlace";
		}
		
		place.setImagePath(file.getOriginalFilename());

		try {
			placeService.uploadAvatar(file);
			placeService.addPlace(place);
		} catch (FileUploadException e) {
			model.addAttribute("fileErrorMsg", e.getMessage());
			model.addAttribute("types", typeService.getAll());
			return "addPlace";
		} catch (PlaceExistsException e) {
			model.addAttribute("nameErrorMsg", "miejsce juz istnieje");
			model.addAttribute("types", typeService.getAll());
			return "addPlace";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMsg", "Nie udalo sie dodac miejsca");
			model.addAttribute("types", typeService.getAll());
			return "addPlace";
		}
		
		
		model.addAttribute("successMsg", "Dodano Miejsce");
		model.addAttribute("place", new Place());
		return "addPlace";
	}
	
	@RequestMapping(value = "placeImage/{imageName:.+}")
	@ResponseBody
	public byte[] getPlaceImage(@PathVariable("imageName") String imageName) throws IOException {
		Path uploadedFolder = Paths.get("uploads/placeImages");
		File serverFile = new File(uploadedFolder + "/" + imageName);
		
		return Files.readAllBytes(serverFile.toPath());
	}
}
