package com.rrivero.model;




import javax.persistence.Entity;
import javax.persistence.Table;import javax.validation.constraints.NotBlank;
@Entity
@Table(name = "perfils")
public class Perfil extends CommonBaseModel{

    @NotBlank
    private String name;

    @NotBlank
    private String description;
    
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
}
