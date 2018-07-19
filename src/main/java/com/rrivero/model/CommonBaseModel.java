package com.rrivero.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * Abstract base model including primary id and created_at and updated_at time
 * fields.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonBaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date created;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modified;

	/** Constructor. */
	protected CommonBaseModel() {
		// Private constructor to prevent direct instantiation.
	}

	/** Primary id getter. */
	public Long getId() {
		return id;
	}

	/** Primary id setter. */
	public void setId(final Long id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
