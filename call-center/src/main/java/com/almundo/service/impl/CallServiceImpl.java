package com.almundo.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.domain.Call;
import com.almundo.repo.CallRepo;
import com.almundo.service.CallService;
import com.google.common.collect.Lists;

@Service
public class CallServiceImpl implements CallService {

	@Autowired	        
	private CallRepo callRepo;
	
	@Override
	public Call create(Call call) {
		return callRepo.save(call);
	}

	@Override
	public void delete(Call call) {
		callRepo.delete(call);
	}

	@Override
	public Boolean exist(Long id) {
		return callRepo.exists(id);
	}

	@Override
	public Collection<Call> findAll() {
		return Lists.newArrayList(callRepo.findAll());
	}
	
	@Override
	public Collection<Call> findAllByCustomer(Long customerId) {
		return Lists.newArrayList(callRepo.findAllByCustomer(customerId));
	}

	@Override
	public Call findOne(Long id) {
		return callRepo.findOne(id);
	}

	@Override
	public Call update(Call call) {
		return callRepo.save(call);
	}

}
