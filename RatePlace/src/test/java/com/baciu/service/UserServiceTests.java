package com.baciu;

import java.util.Date;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baciu.dao.UserDAO;
import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.UsernameExistsException;
import com.baciu.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDAO userDAO;
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	@Test
	public void testGetById() {
		int userId = 14;
		User user = userService.getById(userId);
		
		Assert.assertNotNull("failure - expected not null", user);
	}
	
	@Test
	public void testGetByIdNotFound() {
		int userId = Integer.MAX_VALUE;
		User user = userService.getById(userId);
		
		Assert.assertNull("failure - expected null", user);
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
		try {
			userService.addUser(user, passwordConfirm);
		} catch (Exception e) {
			exception = e;
		}
		User newUser = userDAO.getByUsername(user.getUsername());
		
		Assert.assertNotNull("failure - expected not null", newUser);
		Assert.assertNotNull("failure - expected not null", newUser.getId());
		Assert.assertEquals("failure - expected attribute match", newUser.getUsername(), "test_user1");
		Assert.assertNull("failure - expected null", exception);
	}
	
	@Test
	public void testLogin() {
		String username = "pisak";
		String password = "12345";
		int userId  = userService.logIn(username, password);
		
		Assert.assertEquals("failure - expected attribute match", userId, 12);
	}
	
	@Test
	public void testLoginFailure() {
		String username = "wrong_username";
		String password = "wrong_password";
		int userId  = userService.logIn(username, password);
		
		Assert.assertEquals("failure - expected attribute match", userId, -1);
	}
	
	@Test
	public void testUpdate() {
		Exception exception = null;
		User existedUser = userService.getById(12);
		User newUser = new User();
		newUser.setUsername("NewUsername");
		newUser.setEmail("NewEmail@gmail.com");
		try {
			userService.update(existedUser, newUser);
		} catch (UsernameExistsException | EmailExistsException e) {
			exception = e;
		}
		
		existedUser = userService.getById(12);
		
		Assert.assertEquals("failure - expected attribute match", existedUser.getUsername(), "NewUsername");
		Assert.assertEquals("failure - expected attribute match", existedUser.getEmail(), "NewEmail@gmail.com");
		Assert.assertNull("failure - expected null", exception);
	}
	
}
