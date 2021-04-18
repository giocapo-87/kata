package com.social.kata.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "my_sequence", allocationSize = 1, initialValue = 50000)
	private Long id;

	public Long getId() {
		return id;
	}
	
	
	
}
