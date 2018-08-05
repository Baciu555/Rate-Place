package com.baciu.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Opinion;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Integer>  {
	List<Opinion> findByPlace_Id(int placeId);
}
