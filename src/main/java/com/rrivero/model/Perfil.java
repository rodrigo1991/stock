package com.rrivero.model;




import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "perfils")
@JsonIgnoreProperties(value = {"created", "modified"}, allowGetters = true)
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
