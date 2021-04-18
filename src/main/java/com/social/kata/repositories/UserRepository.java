package com.social.kata.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.social.kata.entities.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	User findByUserName(String userName);
	
}
