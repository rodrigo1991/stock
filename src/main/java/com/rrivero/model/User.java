package com.rrivero.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class User extends CommonBaseModel {
	
    @ManyToOne(optional = false)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Assignment> assignments = new HashSet<>();

    @NotBlank
    private String name;

    @NotBlank
    private String surname;
    
    @NotBlank
    private String username;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String cellphone;
    
    @NotBlank
    private String address;
    
    private double salary;
    
    private Timestamp birthdate;
    
    private boolean active;

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Set<Assignment> getAssignments() {
		return assignments;
	}
	@JsonIgnore
	public void setAssignments(Set<Assignment> assignments) {
		this.assignments = assignments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Timestamp getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Timestamp birthdate) {
		this.birthdate = birthdate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}


}
