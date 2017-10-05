package com.baciu.service;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.baciu.dao.PlaceDAO;
import com.baciu.entity.Place;
import com.baciu.exception.FileUploadException;
import com.baciu.exception.PlaceExistsException;

@Service
public class PlaceService implements IPlaceService {
	
	private final Path UPLOADED_FOLDER = Paths.get("uploads/placeImages");
	
	@Autowired
	private PlaceDAO placeDAO;

	@Override
	public List<Place> getAll() {
		List<Place> places = placeDAO.getAll();
		return places;
	}

	@Override
	public Place getPlace(int id) {
		Place place = placeDAO.getPlace(id);
		return place;
	}

	@Override
	public void addPlace(Place place) throws PlaceExistsException {
		if (placeDAO.placeExists(place.getName()))
			throw new PlaceExistsException("miejsce juz istnieje");
		placeDAO.addPlace(place);
		
	}

	@Override
	public List<Place> getUserPlaces(int userId) {
		List<Place> places = placeDAO.getUserPlaces(userId);
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
