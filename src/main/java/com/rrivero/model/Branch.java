package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branches")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Branch  extends CommonBaseModel{

    private String name;
    
    private String address;
    
    private LocalDate opened;

    private String description;
    
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    private Set<BranchProduct> branchesProducts = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getOpened() {
		return opened;
	}

	public void setOpened(LocalDate opened) {
		this.opened = opened;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Set<BranchProduct> getBranchesProducts() {
		return branchesProducts;
	}

	public void setBranchesProducts(Set<BranchProduct> branchesProducts) {
		this.branchesProducts = branchesProducts;
	}

}
