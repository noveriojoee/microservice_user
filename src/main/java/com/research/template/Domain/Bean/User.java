package com.research.template.Domain.Bean;

import javax.validation.constraints.Size;

public class User {
	private int id;
	@Size(min = 2,max = 100,  message = "Name Length Must be more than 2 and less than 100 characters")
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
