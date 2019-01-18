package com.rrivero.model;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sales")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Sale extends CommonBaseModel{
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;
	
	
}
