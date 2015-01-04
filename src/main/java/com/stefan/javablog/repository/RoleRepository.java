package com.stefan.javablog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stefan.javablog.entity.Role;
import com.stefan.javablog.entity.User;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findRoleByName(String string);
	
}
