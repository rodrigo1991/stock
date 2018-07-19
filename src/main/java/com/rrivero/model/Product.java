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

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    
    private double duration;
    
    @ManyToMany(mappedBy = "tasks")
    Set<User> users = new HashSet<>();

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

   
}
