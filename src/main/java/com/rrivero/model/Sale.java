package com.rrivero.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sales")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Sale extends CommonBaseModel{
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
	
	//se quita comportamiento cascada, para que no me deje null los atributos de branches al actualizar con "branches": [{"id":1},{"id":2}]
	@ManyToMany
    @JoinTable(
        name = "products_sales", 
        joinColumns = { @JoinColumn(name = "product_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "sale_id") }
    )
    private Set<Product> products = new HashSet<>();
	
	private int codigoBarra;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public int getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(int codigoBarra) {
		this.codigoBarra = codigoBarra;
	}
	
	
}
