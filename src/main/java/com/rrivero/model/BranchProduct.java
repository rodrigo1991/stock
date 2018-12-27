package com.rrivero.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "branchesProducts")
@JsonIgnoreProperties(value = { "created", "modified" }, allowGetters = true)
public class BranchProduct extends CommonBaseModel {

	@ManyToOne(optional = false)
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

}
