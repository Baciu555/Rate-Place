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

import com.baciu.entity.User;
import com.baciu.exception.EmailExistsException;
import com.baciu.exception.FileUploadException;
import com.baciu.exception.UsernameExistsException;
import com.baciu.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	private final Path UPLOADED_FOLDER = Paths.get("uploads/avatars");
	private final String DEFAULT_AVATAR = "default-avatar.jpg";
	
	@Autowired
	private UserRepository userRepository;
	
	public User findByUsernameAndPassword() {
		User user = new User();
		user.setUsername("XDD");
		user.setPassword("XDD");
		user = userRepository.findByUsernameAndPassword("pisak", "12345");
		return user;
	}

	@Override
	public int logIn(String username, String password) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user == null)
			return -1;
		
		return user.getId();
	}

	@Override
	public User getById(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public void addUser(User user, String passwordConfirm) throws Exception {
		if (!user.getPassword().equals(passwordConfirm)) {
			throw new Exception("hasla nie sa identyczne");
		}
		
		if (userRepository.findByUsername(user.getUsername()) != null) {
			throw new Exception("nazwa uzytkownika zajeta");
		}
		
		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new Exception("email zajety");
		}
		
		user.setRegisterDate(new Date());
		user.setAvatarPath(DEFAULT_AVATAR);
		userRepository.save(user);

	}

	@Override
	public void update(User user, int userId) throws UsernameExistsException, EmailExistsException {
		User loggedUser = userRepository.findOne(userId);
		if (!loggedUser.getUsername().equals(user.getUsername()))
			if (userRepository.findByUsername(user.getUsername()) != null)
				throw new UsernameExistsException("Nazwa juz zajeta");
		
		if (!loggedUser.getEmail().equals(user.getEmail()))
			if (userRepository.findByEmail(user.getEmail()) != null)
				throw new EmailExistsException("Email juz zajety");
		
		loggedUser.setUsername(user.getUsername());
		loggedUser.setEmail(user.getEmail());
		
		userRepository.save(loggedUser);
	}
	
	@Override
	public void updateAvatar(int userId, String filename) {
		User user = userRepository.findOne(userId);
		user.setAvatarPath(filename);
		userRepository.save(user);
	}

	@Override
	public void uploadAvatar(MultipartFile file) throws FileUploadException {
		if (!isCorrectFileFormat(file))
			throw new FileUploadException("Niepoprawny format pliku");
		
		try {
			Files.copy(file.getInputStream(), UPLOADED_FOLDER.resolve(file.getOriginalFilename()));
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
