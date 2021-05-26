package com.research.template.Services;

import com.research.template.Domain.Bean.User;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;

public interface IUserManagementServices {
	public Resources<UserDTO> saveUser(User requestData);
	public Resources<UserDTO> deleteUser(int id);
	public Resources<UserDTO> getAllUser();
	public Resources<UserDTO> getUserById(int id);
}
