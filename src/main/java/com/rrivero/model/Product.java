package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Product extends CommonBaseModel{
    
	
	//se quita comportamiento cascada, para que no me deje null los atributos de branches al actualizar con "branches": [{"id":1},{"id":2}]
	@ManyToMany
	@JoinTable(
	    name = "branches_products", 
		joinColumns = { @JoinColumn(name = "product_id") }, 
		inverseJoinColumns = { @JoinColumn(name = "branch_id") }
	)	
    private Set<Branch> branches;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToMany(mappedBy = "products") 
    private Set<Sale> sales;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    
    private double price;

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

	public Set<Branch> getBranches() {
		return branches;
	}

	public void setBranches(Set<Branch> branches) {
		this.branches = branches;
	}

	@JsonIgnore
	public Set<Sale> getSales() {
		return sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
   
	
}
