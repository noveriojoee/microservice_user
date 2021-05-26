package com.research.template.Services.impl;

import com.research.template.DAO.UserDAO;
import com.research.template.Domain.Bean.User;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;
import com.research.template.Model.UserModel;
import com.research.template.Services.IUserManagementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserManagementServices implements IUserManagementServices {
	@Autowired
	UserDAO userDAO;

	@Override
	public Resources<UserDTO> saveUser(User requestData) {
		Resources<UserDTO> serviceResult = new Resources<UserDTO>();
		serviceResult.throwResponseWithCode("99","failed to save user",null);

		UserModel userData = this.userDAO.addUser(requestData.getName());
		if (userData != null){
			serviceResult.throwSucceedResponse(new UserDTO());
		}else{
			serviceResult.throwResponseWithCode("99","Failed to save user",null);
		}

		return serviceResult;
	}

	@Override
	public Resources<UserDTO> deleteUser(int id) {
		Resources<UserDTO> serviceResult = new Resources<UserDTO>();

		UserModel deletedUser = userDAO.deleteUser(id);
		if (deletedUser != null){
			serviceResult.throwSucceedResponse(null);
		}else{
			serviceResult.throwResponseWithCode("99","failed to delete user",null);
		}

		return serviceResult;
	}

	@Override
	public Resources<UserDTO> getAllUser() {
		Resources<UserDTO> serviceResult = new Resources<UserDTO>();
		List<UserModel> resultSets =  userDAO.findAll();

		if (resultSets.size() > 0){
			serviceResult.throwSucceedResponse(new UserDTO(resultSets));
		}else{
			serviceResult.throwResponseWithCode("401","Users Empty",null);
		}

		return serviceResult;
	}

	@Override
	public Resources<UserDTO> getUserById(int id) {
		Resources<UserDTO> serviceResult = new Resources<UserDTO>();

		UserModel userData = userDAO.findById(id);
		if (userData != null){
			serviceResult.throwSucceedResponse(new UserDTO(userData));
		}else{
			serviceResult.throwResponseWithCode("99","User Not Found",null);
		}

		return serviceResult;
	}
}
