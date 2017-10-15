package com.baciu;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.Type;
import com.baciu.service.TypeService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TypeServiceTests {
	
	@Autowired
	private TypeService typeService;
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetAll() {
		List<Type> types = typeService.getAll();
		
		Assert.assertNotNull("failure - expected not null", types);
		Assert.assertEquals("failure - expected list size", 3, types.size());
	}

}
