package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class User extends CommonBaseModel{
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "perfil_id")
    private Perfil perfil;

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
    
    @NotBlank
    private double salary;
    
    @NotBlank
    private LocalDate birthdate;
    
    /* patch
 	 *  {
 			"sales":[
 				"task/4",
 				"task/5"
 			]
 		}
      */
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Sale> sales = new HashSet<>();

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	@JsonIgnore
	public Set<Sale> getSales() {
		return sales;
	}

	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

}
