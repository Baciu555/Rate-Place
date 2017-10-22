package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.Type;

@Repository
public interface TypeRepository extends CrudRepository<Type, Integer>  {
}
