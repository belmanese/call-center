package com.almundo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.domain.Customer;
import com.almundo.repo.CustumerRepo;
import com.almundo.service.CustomerService;
import com.google.common.collect.Lists;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired	        
	private CustumerRepo customerRepo;
	
	@Override
	public Customer create(Customer customer) {
		return customerRepo.save(customer);
	}

	@Override
	public void delete(Customer customer) {
		customerRepo.delete(customer);
	}

	@Override
	public Boolean exist(Long id) {
		return customerRepo.exists(id);
	}

	@Override
	public Collection<Customer> findAll() {
		return Lists.newArrayList(customerRepo.findAll());
	}

	@Override
	public Customer findOne(Long id) {
		return customerRepo.findOne(id);
	}

	@Override
	public Customer update(Customer customer) {
		return customerRepo.save(customer);
	}

}
