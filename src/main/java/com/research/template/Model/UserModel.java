package com.research.template.Model;


public class UserModel {
	private int id;
	private String name;

	public UserModel(){
		super();
		this.id = 0;
		this.name = "mr. nobody";
	}

	public UserModel(int id, String name){
		this.id = id;
		this.name = name;
	}

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