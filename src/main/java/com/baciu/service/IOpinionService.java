package com.baciu.service;

import java.util.List;

import com.baciu.entity.Opinion;

public interface IOpinionService {
	
	List<Opinion> getByPlaceId(int placeId);
	Opinion addOpinion(Opinion opinion);

}
