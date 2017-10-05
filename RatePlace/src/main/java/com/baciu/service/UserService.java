package com.baciu.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baciu.dao.UserDAO;
import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.FileUploadException;
import com.baciu.exception.UsernameExistsException;

@Service
public class UserService implements IUserService {
	
	private final Path UPLOADED_FOLDER = Paths.get("uploads/avatars");
	private final String DEFAULT_AVATAR = "default-avatar.jpg";
	
	@Autowired
	private UserDAO userDAO;

	@Override
	public int logIn(String username, String password) {
		User user = userDAO.logIn(username, password);
		if (user == null)
			return -1;
		
		return user.getId();
	}

	@Override
	public User getById(int id) {
		return userDAO.getById(id);
	}

	@Override
	public void addUser(User user, String passwordConfirm) throws Exception {
		if (!user.getPassword().equals(passwordConfirm)) {
			throw new Exception("hasla nie sa identyczne");
		}
		
		if (userDAO.usernameExists(user.getUsername())) {
			System.out.println("1");
			throw new Exception("nazwa uzytkownika zajeta");
		}
		
		if (userDAO.emailExists(user.getEmail())) {
			System.out.println("2");
			throw new Exception("email zajety");
		}
		
		user.setRegisterDate(new Date());
		user.setAvatarPath(DEFAULT_AVATAR);
		userDAO.addUser(user);

	}

	@Override
	public void update(User loggedUser, User user) throws UsernameExistsException, EmailExistsException {
		if (!loggedUser.getUsername().equals(user.getUsername()))
			if (userDAO.usernameExists(user.getUsername()))
				throw new UsernameExistsException("Nazwa juz zajeta");
		
		if (!loggedUser.getEmail().equals(user.getEmail()))
			if (userDAO.emailExists(user.getEmail()))
				throw new EmailExistsException("Email juz zajety");
		
		user.setId(loggedUser.getId());
		
		userDAO.update(user);

	}
	
	@Override
	public void updateAvatar(User user) {
		userDAO.updateAvatar(user);
	}

	@Override
	public void uploadAvatar(MultipartFile file) throws FileUploadException {
		if (!isCorrectFileFormat(file))
			throw new FileUploadException("Niepoprawny format pliku");
		
		try {
			Files.copy(file.getInputStream(), UPLOADED_FOLDER.resolve(file.getOriginalFilename()));
			System.out.println(UPLOADED_FOLDER + " | " + file.getOriginalFilename());
			System.out.println("hehe");
		} catch (FileAlreadyExistsException fileAlreadyExistsException) {
			fileAlreadyExistsException.printStackTrace();
			throw new FileUploadException("Nazwa pliku już istnieje, aby załadować plik zmień jego nazwe");
		} catch (IOException ioException) {
			ioException.printStackTrace();
			throw new FileUploadException("Wystąpił błąd spróbuj ponownie");
		}
	}
	
	private boolean isCorrectFileFormat(MultipartFile file) {
		String[] correctFileFormats = {"image/gif", "image/jpeg", "image/png"};
		if (Arrays.asList(correctFileFormats).contains(file.getContentType()))
			return true;
		
		return false;
	}

	@Override
	public void deleteAvatarFile(String avatarName) throws Exception {
		try {
			File file = new File(UPLOADED_FOLDER + "/" + avatarName);
			file.delete();
		} catch (NullPointerException nullPointerException) {
			throw new Exception("Plik do usuniecia nie istnieje");
		}
	}

}
