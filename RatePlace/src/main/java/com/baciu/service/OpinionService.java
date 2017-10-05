package com.baciu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baciu.dao.OpinionDAO;
import com.baciu.entity.Opinion;

@Service
public class OpinionService implements IOpinionService {
	
	@Autowired
	private OpinionDAO opinionDAO;

	@Override
	public List<Opinion> getByPlaceId(int placeId) {
		List<Opinion> opinions = opinionDAO.getByPlaceId(placeId);
		return opinions;
	}

	@Override
	public void addOpinion(Opinion opinion) {
		opinion.setEntryDate(new Date());
		opinionDAO.addOpinion(opinion);
	}

}
