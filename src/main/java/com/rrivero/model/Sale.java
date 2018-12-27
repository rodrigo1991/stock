package com.rrivero.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sales")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Sale extends CommonBaseModel{
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "branchProduct_id")
    private BranchProduct branchProduct;
	
	
}
