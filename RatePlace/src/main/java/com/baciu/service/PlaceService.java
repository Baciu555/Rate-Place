package com.baciu.service;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baciu.entity.Opinion;
import com.baciu.entity.Place;
import com.baciu.entity.User;
import com.baciu.exception.FileUploadException;
import com.baciu.exception.PlaceExistsException;
import com.baciu.repository.PlaceRepository;
import com.baciu.repository.UserRepository;

@Service
public class PlaceService implements IPlaceService {
	
	private final Path UPLOADED_FOLDER = Paths.get("uploads/placeImages");
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<Place> getAll() {
		List<Place> places = (List<Place>) placeRepository.findAll();
		return places;
	}

	@Override
	public Place getPlace(int id) {
		Place place = placeRepository.findOne(id);
		return place;
	}

	@Override
	public void addPlace(Place place) throws PlaceExistsException {
		if (placeRepository.findByName(place.getName()) != null)
			throw new PlaceExistsException("miejsce juz istnieje");
		placeRepository.save(place);
		
	}

	@Override
	public List<Place> getUserVisitedPlaces(int userId) {
		User user = userRepository.findOne(userId);
		Set<Opinion> opinions = user.getOpinions();
		List<Place> places = new ArrayList<>();
		opinions.forEach(x -> places.add(x.getPlace()));
		return places;
	}

	@Override
	public void uploadAvatar(MultipartFile file) throws FileUploadException {
		if (file.isEmpty())
			throw new FileUploadException("prosze wybrac plik");
		
		if (!isCorrectFileFormat(file)) 
			throw new FileUploadException("nieprawidlowy format pliku");
			
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

}
