package com.almundo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.almundo.domain.Customer;

@Service
public interface CustomerService {

	Customer create(Customer customer);
	
	void delete(Customer customer);
	
	Boolean exist(Long id);
	
	Collection<Customer> findAll();
	
	Customer findOne(Long id);
	
	Customer update(Customer customer);
	
}
