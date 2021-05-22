package com.research.template.Domain.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.research.template.Model.UserModel;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

	private List<UserModel> users;


	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
}
