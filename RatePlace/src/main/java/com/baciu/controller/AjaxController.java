package com.baciu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baciu.entity.AjaxResponse;
import com.baciu.entity.Place;
import com.baciu.service.PlaceService;

@RestController
public class AjaxController {
	
	@Autowired
	private PlaceService placeService;

	@RequestMapping(value = "main/api", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AjaxResponse> getSearchResultViaAjax(@RequestBody String message, Errors errors) {
		if (errors.hasErrors()) {
			AjaxResponse response = new AjaxResponse("FALSE", null);
			return new ResponseEntity<AjaxResponse>(response, HttpStatus.BAD_REQUEST);
		}

		List<Place> places = placeService.getAll();
		AjaxResponse response = new AjaxResponse("DONE", places);
		
		return new ResponseEntity<AjaxResponse>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "main/place/{id}", method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> getPlace(@RequestBody String message, @PathVariable("id") int id, Errors errors) {
		
		if (errors.hasErrors()) {
			AjaxResponse response = new AjaxResponse("FAIL", null);
			return new ResponseEntity<AjaxResponse>(response, HttpStatus.BAD_REQUEST);
		}
		
		Place place = placeService.getPlace(id);
		AjaxResponse response = new AjaxResponse("Done", place);
		
		return new ResponseEntity<AjaxResponse>(response, HttpStatus.OK);
	}
}
