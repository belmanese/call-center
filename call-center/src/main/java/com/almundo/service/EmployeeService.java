package com.almundo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.almundo.domain.Employee;
import com.almundo.domain.Role;

@Service
public interface EmployeeService {

	Employee create(Employee employee);
	
	void delete(Employee employee);
	
	Boolean exist(Long id);
	
	Collection<Employee> findAll();
	
	Collection<Employee> findByFree(Boolean free);

	Collection<Employee> findByRoleAndFree(Role role, Boolean free);
	
	Employee findOne(Long id);
	
	Employee update(Employee employee);
	
}