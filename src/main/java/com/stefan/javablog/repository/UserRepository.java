package com.stefan.javablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefan.javablog.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);
	
}
