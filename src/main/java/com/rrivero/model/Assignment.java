package com.rrivero.model;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "assignments")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
public class Assignment  extends CommonBaseModel{
	
	@OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
	private Set<Sale> sales = new HashSet<>();
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "branch_id")
    private Branch branch;
	
	private Timestamp from;
	
	private Timestamp to;
	
	

	public Set<Sale> getSales() {
		return sales;
	}
	@JsonIgnore
	public void setSales(Set<Sale> sales) {
		this.sales = sales;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Timestamp getFrom() {
		return from;
	}

	public void setFrom(Timestamp from) {
		this.from = from;
	}

	public Timestamp getTo() {
		return to;
	}

	public void setTo(Timestamp to) {
		this.to = to;
	}
	
	
}
