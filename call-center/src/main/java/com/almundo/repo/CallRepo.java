package com.almundo.repo;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.almundo.domain.Call;

public interface CallRepo extends CrudRepository<Call, Long> {
	
	@Query("SELECT ca FROM Call ca INNER JOIN ca.customer cu WHERE cu.costumerId = ?1")
	public Collection<Call> findAllByCustomer(Long customerId);
	
}