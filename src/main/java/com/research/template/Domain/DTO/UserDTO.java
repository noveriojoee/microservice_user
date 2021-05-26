package com.research.template.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.research.template.Model.UserModel;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private List<UserModel> users;
	private UserModel user;

	public UserDTO(){
		super();
		this.users = null;
		this.user = null;
	}

	public UserDTO(UserModel user){
		this.user =  user;
	}

	public UserDTO(List<UserModel> users){
		this.users = users;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
