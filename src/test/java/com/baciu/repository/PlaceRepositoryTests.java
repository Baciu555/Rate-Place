package com.baciu.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.Place;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlaceRepositoryTests {
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Test
	public void testFindByName() {
		Place place = placeRepository.findByName("Hotel Ambasadorski");
		assertThat(place.getName()).isEqualTo("Hotel Ambasadorski");
		assertThat(place.getId()).isEqualTo(2);
		assertThat(place.getLatitude()).isEqualTo(50.037404);
		assertThat(place.getLongitude()).isEqualTo(22.005822);
		assertThat(place.getType().getId()).isEqualTo(3);
	}
	
	@Test
	public void testFindByNameFailure() {
		Place place = placeRepository.findByName("no Existing Place");
		assertThat(place).isNull();
	}

}
