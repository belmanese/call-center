package com.almundo.repo;

import org.springframework.data.repository.CrudRepository;

import com.almundo.domain.Role;

public interface RoleRepo extends CrudRepository<Role, Long> {}