package com.almundo.repo;

import org.springframework.data.repository.CrudRepository;

import com.almundo.domain.Customer;

public interface CustumerRepo extends CrudRepository<Customer, Long> {}