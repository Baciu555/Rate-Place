package com.baciu.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baciu.entity.Place;
import com.baciu.exception.PlaceExistsException;

public interface IPlaceService {
	
	List<Place> getAll();
	Place getPlace(int id);
	void addPlace(Place place) throws PlaceExistsException;
	List<Place> getUserVisitedPlaces(int userId);
	void uploadAvatar(MultipartFile file) throws Exception;

}
