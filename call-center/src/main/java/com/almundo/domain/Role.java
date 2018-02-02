package com.almundo.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@SequenceGenerator(name="role_seq", sequenceName="role_role_id_seq")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Column(name = "role_id")
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="role_seq")
	private Long roleId;
	
	@Getter @Setter
	private String name;

	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private Collection<Employee> employee;

}
