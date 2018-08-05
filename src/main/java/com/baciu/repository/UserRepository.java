package com.baciu.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.baciu.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsernameAndPassword(String username, String password);
	User findByUsername(String username);
	User findByEmail(String email);
}
