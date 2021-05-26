package com.research.template.Services;

import com.research.template.Domain.Bean.User;

public interface IUserManagementServices {
	public void saveUser(User requestData);
	public void deleteUser(int id);
	public void getAllUser();
	public void getUserById(int id);
}
