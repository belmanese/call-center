package com.almundo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.domain.Role;
import com.almundo.repo.RoleRepo;
import com.almundo.service.RoleService;
import com.google.common.collect.Lists;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired	        
	private RoleRepo roleRepo;
	
	@Override
	public Role create(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public void delete(Role role) {
		roleRepo.delete(role);
	}

	@Override
	public Boolean exist(Long id) {
		return roleRepo.exists(id);
	}

	@Override
	public Collection<Role> findAll() {
		return Lists.newArrayList(roleRepo.findAll());
	}

	@Override
	public Role findOne(Long id) {
		return roleRepo.findOne(id);
	}

	@Override
	public Role update(Role role) {
		return roleRepo.save(role);
	}

}
