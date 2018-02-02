package com.almundo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.almundo.domain.Call;

@Service
public interface CallService {

	Call create(Call call);
	
	void delete(Call call);
	
	Boolean exist(Long id);

	Collection<Call> findAll();

	Collection<Call> findAllByCustomer(Long customerId);
	
	Call findOne(Long id);
	
	Call update(Call call);
	
}
