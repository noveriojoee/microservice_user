package com.research.template.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_user_mt",schema = "dbo_user_mgmt")
public class UserModel {
	@Id
	@Column(name = "user_id")
	private int id;
	@Column(name = "nama")
	private String name;

	public UserModel(){
		super();
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
