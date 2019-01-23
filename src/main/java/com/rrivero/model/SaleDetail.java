package com.rrivero.model;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "saleDetails")
public class SaleDetail extends CommonBaseModel{
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "sale_id")
    private Sale sale;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "branch_product_id")
    private BranchProduct branchProduct;
	
	private int quantity;

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public BranchProduct getBranchProduct() {
		return branchProduct;
	}

	public void setBranchProduct(BranchProduct branchProduct) {
		this.branchProduct = branchProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
