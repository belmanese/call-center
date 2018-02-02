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
@SequenceGenerator(name="customer_seq", sequenceName="customer_customer_id_seq")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	@Column(name = "customer_id")
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	private Long costumerId;

	@Getter @Setter
	private String identification;
	
	@Getter @Setter
	private String name;

	@Setter
	@JsonIgnore
	@OneToMany(mappedBy = "customer")
	private Collection<Call> calls;

}
