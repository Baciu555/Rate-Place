package com.baciu.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.UsernameExistsException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTests {

	@Autowired
	private UserService userService;
	
	@Test
	public void testGetById() {
		int userId = 14;
		User user = userService.getById(userId);
		
		assertThat(user).isNotNull();
		assertThat(user.getId()).isEqualTo(14);
		assertThat(user.getUsername()).isEqualTo("banan");
		assertThat(user.getEmail()).isEqualTo("banan@wp.pl");
		assertThat(user.getAvatarPath()).isEqualTo("default-avatar.jpg");
	}
	
	@Test
	public void testGetByIdNotFound() {
		int userId = Integer.MAX_VALUE;
		User user = userService.getById(userId);
		
		assertThat(user).isNull();
	}
	
	@Test
	public void testAddUser() {
		Exception exception = null;
		User user = new User();
		user.setUsername("test_user1");
		user.setPassword("test_password1");
		user.setEmail("test_email1@gmail.com");
		user.setAvatarPath("default-avatar.jpg");
		user.setRegisterDate(new Date());
		String passwordConfirm = "test_password1";
		User newUser = new User();
		try {
			newUser = userService.addUser(user, passwordConfirm);
		} catch (Exception e) {
			exception = e;
		}
		
		assertThat(newUser).isNotNull();
		assertThat(newUser.getId()).isNotNull();
		assertThat(newUser.getUsername()).isEqualTo("test_user1");
		assertThat(newUser.getPassword()).isEqualTo("test_password1");
		assertThat(newUser.getEmail()).isEqualTo("test_email1@gmail.com");
		assertThat(newUser.getAvatarPath()).isEqualTo("default-avatar.jpg");
		assertThat(exception).isNull();
	}
	
	@Test
	public void testLogin() {
		int userId  = userService.logIn("pisak", "12345");
		
		assertThat(userId).isEqualTo(12);
	}
	
	@Test
	public void testLoginFailure() {
		int userId  = userService.logIn("wrong_username", "wrong_password");
		
		assertThat(userId).isEqualTo(-1);
	}
	
	@Test
	public void testUpdate() {
		Exception exception = null;
		User existedUser = new User();
		User newUser = new User();
		newUser.setUsername("NewUsername");
		newUser.setEmail("NewEmail@gmail.com");
		try {
			existedUser = userService.update(newUser, 13);
		} catch (UsernameExistsException | EmailExistsException e) {
			exception = e;
		}
		
		assertThat(existedUser).isNotNull();
		assertThat(existedUser.getUsername()).isEqualTo("NewUsername");
		assertThat(existedUser.getEmail()).isEqualTo("NewEmail@gmail.com");
		assertThat(exception).isNull();
	}
	
}
