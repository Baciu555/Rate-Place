package com.baciu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.Type;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class TypeServiceTests {
	
	@Autowired
	private TypeService typeService;
	
	@Test
	public void testGetAll() {
		List<Type> types = typeService.getAll();
		
		assertThat(types).isNotEmpty();
		assertThat(types.size()).isEqualTo(3);
	}

}
