package com.baciu.dao;

import java.util.List;

import com.baciu.entity.Place;

public interface IPlaceDAO {

	List<Place> getAll();
	Place getPlace(int id);
	void addPlace(Place place);
	List<Place> getUserPlaces(int userId);
	boolean placeExists(String name);
}
