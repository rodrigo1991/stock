package com.rrivero.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "branchesProducts")
@JsonIgnoreProperties(value = { "created", "modified" }, allowGetters = true)
public class BranchProduct extends CommonBaseModel {

	@OneToMany(mappedBy = "branchProduct", cascade = CascadeType.ALL)
	private Set<SaleDetail> saleDetails = new HashSet<>();

	@ManyToOne(optional = false)
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;
	
	
	public Set<SaleDetail> getSaleDetails() {
		return saleDetails;
	}
	
	@JsonIgnore
	public void setSaleDetails(Set<SaleDetail> saleDetails) {
		this.saleDetails = saleDetails;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
