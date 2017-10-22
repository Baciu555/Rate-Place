package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Place;

@Repository
public interface PlaceRepository extends CrudRepository<Place, Integer>  {

	Place findByName(String name);
}
