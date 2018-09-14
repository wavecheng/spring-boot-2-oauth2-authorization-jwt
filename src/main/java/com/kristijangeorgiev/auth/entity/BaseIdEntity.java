package com.kristijangeorgiev.auth.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * 
 * <h2>BaseIdEntity</h2>
 * 
 * @author Kristijan Georgiev
 * 
 *         MappedSuperclass that extends the {@link BaseEntity} class and is
 *         extended by entity classes that have ID field of type Long
 *
 */

@MappedSuperclass

public class BaseIdEntity extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
}
