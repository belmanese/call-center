package com.almundo.repo;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.almundo.domain.Employee;
import com.almundo.domain.Role;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

	Collection<Employee> findByFree(Boolean free);

	Collection<Employee> findByRoleAndFree(Role role, Boolean free);
	
}