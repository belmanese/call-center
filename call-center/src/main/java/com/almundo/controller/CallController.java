package com.almundo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.domain.Call;
import com.almundo.service.CallService;
import com.almundo.service.CustomerService;
import com.almundo.service.EmployeeService;
import com.google.common.collect.Lists;

@RestController
@RequestMapping("/call")
public class CallController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CallService callService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Call> create(@RequestBody Call call) {
		call.setCustomer(customerService.findOne(call.getCustomer().getCostumerId()));
		call.setEmployee(employeeService.findOne(call.getEmployee().getEmployeeId()));
		return new ResponseEntity<>(callService.create(call), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Call>> findAll() {
		return new ResponseEntity<>(Lists.newArrayList(callService.findAll()), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Call> findOne(@PathVariable("id") Long id) {
		return new ResponseEntity<>(callService.findOne(id), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Call> update(@RequestBody Call call) {
		call.setCustomer(callService.findOne(call.getCallId()).getCustomer());
		return new ResponseEntity<>(callService.update(call), HttpStatus.OK);
	}


}