package com.almundo.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@SequenceGenerator(name="employee_seq", sequenceName="employee_employee_id_seq")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Column(name = "employee_id")
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_seq")
	private Long employeeId;

	@Getter @Setter
	private Boolean free;
	
	@Getter @Setter
	private String name;

	@Getter @Setter
	private String number;

	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private Collection<Call> calls;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

}
