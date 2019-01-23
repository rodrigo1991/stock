package com.rrivero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "branchesProducts")
public class BranchProduct extends CommonBaseModel {

	@ManyToOne(optional = false)
	@JoinColumn(name = "branch_id")
	private Branch branch;

	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;

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
