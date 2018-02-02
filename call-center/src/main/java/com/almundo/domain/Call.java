package com.almundo.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter @Setter
@SequenceGenerator(name="call_seq", sequenceName="call_call_id_seq")
public class Call implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "call_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="call_seq")
	private Long callId;

	private Timestamp end;

	private Timestamp start;

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	@Transient
	private Integer timeCall;

}
