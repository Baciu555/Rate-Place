package com.baciu.service;

import org.springframework.web.multipart.MultipartFile;

import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.UsernameExistsException;

public interface IUserService {
	
	int logIn(String username, String password);
	User getById(int id);
	void addUser(User user, String passwordConfirm) throws Exception;
	void update(User loggedUser, User user) throws UsernameExistsException, EmailExistsException;
	void updateAvatar(User user);
	void uploadAvatar(MultipartFile file) throws Exception;
	void deleteAvatarFile(String avatarName) throws Exception;

}
