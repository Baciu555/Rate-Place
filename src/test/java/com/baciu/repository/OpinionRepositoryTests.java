package com.baciu.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.Opinion;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OpinionRepositoryTests {
	
	@Autowired
	private OpinionRepository opinionRepository;
	
	@Test
	public void testFindByPlaceId()  {
		List<Opinion> opinions = opinionRepository.findByPlace_Id(1);
		assertThat(opinions).isNotNull();
		assertThat(opinions.size()).isEqualTo(1);
	}
	
	@Test
	public void testFindByPlaceIdFailure() {
		List<Opinion> opinions = opinionRepository.findByPlace_Id(Integer.MAX_VALUE);
		assertThat(opinions).isEmpty();
	}

}
