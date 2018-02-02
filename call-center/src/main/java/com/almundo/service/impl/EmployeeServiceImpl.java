package com.almundo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.domain.Employee;
import com.almundo.domain.Role;
import com.almundo.repo.EmployeeRepo;
import com.almundo.service.EmployeeService;
import com.google.common.collect.Lists;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired	        
	private EmployeeRepo employeeRepo;
	
	@Override
	public Employee create(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public void delete(Employee employee) {
		employeeRepo.delete(employee);
	}

	@Override
	public Boolean exist(Long id) {
		return employeeRepo.exists(id);
	}

	@Override
	public Collection<Employee> findAll() {
		return Lists.newArrayList(employeeRepo.findAll());
	}

	@Override
	public Collection<Employee> findByFree(Boolean free) {
		return employeeRepo.findByFree(free);
	}

	@Override
	public Collection<Employee> findByRoleAndFree(Role role, Boolean free) {
		return employeeRepo.findByRoleAndFree(role, free);
	}
	
	@Override
	public Employee findOne(Long id) {
		return employeeRepo.findOne(id);
	}

	@Override
	public Employee update(Employee employee) {
		return employeeRepo.save(employee);
	}

}