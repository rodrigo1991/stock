package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Product extends CommonBaseModel{
    
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
