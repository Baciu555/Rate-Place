package com.baciu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.entity.Opinion;
import com.baciu.repository.OpinionRepository;

@Service
public class OpinionService implements IOpinionService {
	
	@Autowired
	private OpinionRepository opinionRepository;

	@Override
	public List<Opinion> getByPlaceId(int placeId) {
		List<Opinion> opinions = opinionRepository.findByPlace_Id(placeId);
		return opinions;
	}

	@Override
	public void addOpinion(Opinion opinion) {
		opinion.setEntryDate(new Date());
		opinionRepository.save(opinion);
	}

}
