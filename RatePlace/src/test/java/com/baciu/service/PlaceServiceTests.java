package com.baciu;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.dao.PlaceDAO;
import com.baciu.entity.Place;
import com.baciu.entity.Type;
import com.baciu.exception.PlaceExistsException;
import com.baciu.service.PlaceService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceServiceTests {
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private PlaceDAO placeDAO;

	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
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
		try {
			placeService.addPlace(place);
		} catch (PlaceExistsException e) {
			exception = e;
		}
		
		Place newPlace = placeDAO.getByPlaceName("New Name");
		
		Assert.assertNotNull("failure - expected not null", newPlace);
		Assert.assertEquals(newPlace.getName(), "New Name");
		Assert.assertEquals(newPlace.getDescription(), "New Description");
		Assert.assertNull("failure - expected null", exception);
	}
	
	@Test
	public void testGetAll() {
		List<Place> places = placeService.getAll();
		
		Assert.assertNotNull("failure - expected not null", places);
		Assert.assertEquals(places.size(), 10);
	}
	
	@Test
	public void testGetUserPlaces() {
		List<Place> places = placeService.getUserPlaces(12);
		
		Assert.assertNotNull("failure - expected not null", places);
		Assert.assertEquals(places.size(), 2);
	}
	
}
