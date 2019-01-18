package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Product extends CommonBaseModel{
    
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<BranchProduct> branchesProducts = new HashSet<>();
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    
    private double price;
    
    private String barcode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<BranchProduct> getBranchesProducts() {
		return branchesProducts;
	}

	public void setBranchesProducts(Set<BranchProduct> branchesProducts) {
		this.branchesProducts = branchesProducts;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
   
	
}
