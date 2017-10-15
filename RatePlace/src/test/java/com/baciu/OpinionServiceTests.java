package com.baciu;

import java.util.Date;
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

import com.baciu.dao.OpinionDAO;
import com.baciu.entity.Opinion;
import com.baciu.entity.Place;
import com.baciu.entity.User;
import com.baciu.service.OpinionService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OpinionServiceTests {
	
	@Autowired
	private OpinionService opinionService;
	
	@Autowired
	private OpinionDAO opinionDAO;
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
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
		
		opinionService.addOpinion(opinion);
		Opinion newOpinion = opinionDAO.getOpinionByContent("New Opinion");
		
		Assert.assertNotNull("failure - expected not null", newOpinion);
		Assert.assertEquals(newOpinion.getContent(), "New Opinion");
		Assert.assertEquals(newOpinion.getPlace().getId(), new Integer(1));
		Assert.assertEquals(newOpinion.getUser().getId(), new Integer(12));
	}
	
	@Test
	public void testGetByPlaceId() {
		List<Opinion> opinions = opinionService.getByPlaceId(1);
		
		Assert.assertNotNull("failure - expected not null", opinions);
		Assert.assertEquals("failure - expected list size", 1, opinions.size());
	}

}
