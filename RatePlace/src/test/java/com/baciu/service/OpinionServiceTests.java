package com.baciu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.Opinion;
import com.baciu.entity.Place;
import com.baciu.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OpinionServiceTests {
	
	@Autowired
	private OpinionService opinionService;
	
	@Test
	public void testAddOpinion() {
		Opinion opinion = new Opinion();
		opinion.setContent("New Opinion");
		opinion.setEntryDate(new Date());
		opinion.setGrade(5);
		Place place = new Place();
		place.setId(1);
		opinion.setPlace(place);
		User user = new User();
		user.setId(12);
		opinion.setUser(user);
		
		Opinion newOpinion = opinionService.addOpinion(opinion);
		
		assertThat(newOpinion.getContent()).isEqualTo("New Opinion");
		assertThat(newOpinion.getGrade()).isEqualTo(5);
		assertThat(newOpinion.getPlace().getId()).isEqualTo(1);
		assertThat(newOpinion.getUser().getId()).isEqualTo(12);
	}
	
	@Test
	public void testGetByPlaceId() {
		List<Opinion> opinions = opinionService.getByPlaceId(1);
		
		assertThat(opinions).isNotNull();
		assertThat(opinions.size()).isEqualTo(1);
	}
	
	@Test
	public void testGetByPlaceIdFailure() {
		List<Opinion> opinions = opinionService.getByPlaceId(Integer.MAX_VALUE);
		
		assertThat(opinions).isEmpty();
	}

}
