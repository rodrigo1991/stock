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
	
	private Timestamp begin;
	
	private Timestamp end;
	
	

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
	public Timestamp getBegin() {
		return begin;
	}
	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}	
	
}
