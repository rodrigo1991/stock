package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sales")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Sale  extends CommonBaseModel{

    @NotBlank
    private String name;
    
    @NotBlank
    private String address;
    
    @NotBlank
    private LocalDate opened;

    @NotBlank
    private String description;
    
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
    Set<Product> tasks = new HashSet<>();

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
	public Set<Product> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Product> tasks) {
		this.tasks = tasks;
	}

}
