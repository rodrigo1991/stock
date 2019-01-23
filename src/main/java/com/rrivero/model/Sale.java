package com.rrivero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sales")
public class Sale extends CommonBaseModel{
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
	
	
}
