package com.baciu.dao;

import java.util.List;

import com.baciu.entity.User;

public interface IUserDAO {
	
	User logIn(String username, String password);
	User getById(int id);
	void addUser(User user);
	void update(User user);
	void updateAvatar(User user);
	List<User> getAll();
	boolean usernameExists(String username);
	boolean emailExists(String email);

}
