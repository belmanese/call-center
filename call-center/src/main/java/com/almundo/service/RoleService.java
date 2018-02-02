package com.almundo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.almundo.domain.Role;

@Service
public interface RoleService {

	Role create(Role role);
	
	void delete(Role role);
	
	Boolean exist(Long id);
	
	Collection<Role> findAll();
	
	Role findOne(Long id);
	
	Role update(Role role);
	
}
