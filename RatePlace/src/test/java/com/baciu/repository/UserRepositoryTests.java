package com.baciu.repository;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testFindByEmail() {
		User user = userRepository.findByEmail("baciu@gmail.com");
		assertThat(user.getUsername()).isEqualTo("baciu");
		assertThat(user.getEmail()).isEqualTo("baciu@gmail.com");
		assertThat(user.getId()).isEqualTo(13);
		assertThat(user.getAvatarPath()).isEqualTo("default-avatar.jpg");
	}
	
	@Test
	public void testFindByEmailFailure() {
		User user = userRepository.findByEmail("fakeEmail@gmail.com");
		assertThat(user).isNull();
	}
	
	@Test
	public void testFindByUsername() {
		User user = userRepository.findByUsername("baciu");
		assertThat(user.getUsername()).isEqualTo("baciu");
		assertThat(user.getEmail()).isEqualTo("baciu@gmail.com");
		assertThat(user.getId()).isEqualTo(13);
		assertThat(user.getAvatarPath()).isEqualTo("default-avatar.jpg");
	}
	
	@Test
	public void testFindByUsernameFailure() {
		User user = userRepository.findByUsername("noExistingUsername");
		assertThat(user).isNull();
	}
	
	@Test
	public void testFindByUsernameAndPassword() {
		User user = userRepository.findByUsernameAndPassword("baciu", "123456");
		assertThat(user.getUsername()).isEqualTo("baciu");
		assertThat(user.getEmail()).isEqualTo("baciu@gmail.com");
		assertThat(user.getId()).isEqualTo(13);
		assertThat(user.getAvatarPath()).isEqualTo("default-avatar.jpg");
	}
	
	@Test
	public void testFindByUsernameAndPasswordFailure() {
		User user = userRepository.findByUsernameAndPassword("badUsername", "badPassword");
		assertThat(user).isNull();
	}

}
