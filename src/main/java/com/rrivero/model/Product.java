package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Product extends CommonBaseModel{
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    
    private double duration;
    
	public Branch getProject() {
		return branch;
	}

	public void setProject(Branch branch) {
		this.branch = branch;
	}

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

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}


   
}
