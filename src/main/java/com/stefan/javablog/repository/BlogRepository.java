package com.stefan.javablog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefan.javablog.entity.Blog;
import com.stefan.javablog.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>{
	
	List<Blog> findByUser(User user); 
	
}
