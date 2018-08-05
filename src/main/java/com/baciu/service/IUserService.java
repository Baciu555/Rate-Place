package com.baciu.service;

import org.springframework.web.multipart.MultipartFile;

import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.UsernameExistsException;

public interface IUserService {
	
	int logIn(String username, String password);
	User getById(int id);
	User addUser(User user, String passwordConfirm) throws Exception;
	User update(User user, int userId) throws UsernameExistsException, EmailExistsException;
	User updateAvatar(int userId, String filename);
	void uploadAvatar(MultipartFile file) throws Exception;
	void deleteAvatarFile(String avatarName) throws Exception;

}
