package com.baciu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.Place;
import com.baciu.entity.Type;
import com.baciu.exception.PlaceExistsException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceServiceTests {
	
	@Autowired
	private PlaceService placeService;
	
	@Test
	public void testAddPlace() {
		Exception exception = null;
		Place place = new Place();
		place.setDescription("New Description");
		place.setLatitude(2.2222);
		place.setLongitude(4.4444);
		place.setName("New Name");
		place.setImagePath("File Path");
		Type type = new Type();
		type.setId(1);
		place.setType(type);
		Place newPlace = new Place();
		try {
			newPlace = placeService.addPlace(place);
		} catch (PlaceExistsException e) {
			exception = e;
		}
		
		assertThat(newPlace).isNotNull();
		assertThat(newPlace.getName()).isEqualTo(place.getName());
		assertThat(newPlace.getDescription()).isEqualTo(place.getDescription());
		assertThat(newPlace.getType().getId()).isEqualTo(place.getType().getId());
		assertThat(exception).isNull();
	}
	
	@Test
	public void testGetAll() {
		List<Place> places = placeService.getAll();
		
		assertThat(places).isNotEmpty();
		assertThat(places.size()).isEqualTo(12);
	}
	
	@Test
	public void getPlace() {
		Place place = placeService.getPlace(1);
		
		assertThat(place).isNotNull();
		assertThat(place.getName()).isEqualTo("Dara Kebab");
		assertThat(place.getId()).isEqualTo(1);
	}
	
	@Test
	public void getPlaceFail() {
		Place place = placeService.getPlace(Integer.MAX_VALUE);
		
		assertThat(place).isNull();
	}
	
	@Test
	public void testGetUserVisitedPlaces() {
		List<Place> places = placeService.getUserVisitedPlaces(12);
		
		assertThat(places).isNotEmpty();
		assertThat(places.size()).isEqualTo(2);
	}
	
}
