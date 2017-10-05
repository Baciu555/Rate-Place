package com.baciu.dao;

import java.util.List;

import com.baciu.entity.Opinion;

public interface IOpinionDAO {
	
	List<Opinion> getByPlaceId(int placeId);
	void addOpinion(Opinion opinion);

}
