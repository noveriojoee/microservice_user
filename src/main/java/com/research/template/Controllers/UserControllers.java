package com.research.template.Controllers;

import com.google.gson.Gson;
import com.research.template.DAO.UserDAO;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;
import com.research.template.Model.UserModel;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/users/v1"})
public class UserControllers {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(method = RequestMethod.POST)
	public Resources<UserDTO> saveUser(@RequestBody UserDTO requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO dto = new UserDTO();

		dto.setUser(userDAO.addUser(requestData.getUser().getName()));

		responseData.throwSucceedResponse(dto);

		return responseData;
	}

	@RequestMapping(value = {"/all"}, method = RequestMethod.GET)
	public Resources<UserDTO> getUserAll(HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO userDomain = new UserDTO();

		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		userDomain.setUsers(userDAO.findAll());

		responseData.throwSucceedResponse(userDomain);

		return responseData;
	}

	///NOTES : @PathVariable refering to {id}
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
	public Resources<UserDTO> getUserById(@PathVariable String id, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO userDomain = new UserDTO();

		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		try {
			int tempId = Integer.parseInt(id);
			userDomain.setUser(userDAO.findById(tempId));

			responseData.throwSucceedResponse(userDomain);
			servletResponse.setStatus(200);
		} catch (Exception e) {
			responseData.throwExceptionResponse(e.getMessage());
			servletResponse.setStatus(400);
		}

		return responseData;
	}

	@RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
	public Resources<UserDTO> deleteUserById(@PathVariable String id, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();

		try {
			int tempId = Integer.parseInt(id);
			UserModel deletedData = this.userDAO.deleteUser(tempId);
			if (deletedData != null){
				responseData.throwSucceedResponse(null);
			}else{
				responseData.throwResponseWithCode("03","data not found",null);
			}

			servletResponse.setStatus(200);
		} catch (Exception e) {
			responseData.throwExceptionResponse(e.getMessage());
			servletResponse.setStatus(400);
		}

		return responseData;
	}
}
