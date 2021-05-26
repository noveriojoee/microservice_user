package com.research.template.Controllers;

import com.google.gson.Gson;
import com.research.template.DAO.UserDAO;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;
import com.research.template.Domain.Bean.User;
import com.research.template.Model.UserModel;
import com.research.template.Services.IUserManagementServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/users"})
public class UserControllers {
	@Autowired
	IUserManagementServices userServices;

	@RequestMapping(value = {"/v1"}, method = RequestMethod.POST)
	public Resources<UserDTO> saveUser(@RequestBody User requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		try {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(requestData);
			Optional<ConstraintViolation<User>> validatedObject = violations.stream().findFirst();
			if (validatedObject.isEmpty()) {
				responseData = this.userServices.saveUser(requestData);
			} else {
				responseData.throwResponseWithCode("405", "Bad Request " + validatedObject.get().getMessage(), null);
			}
		} catch (Exception e) {
			responseData.throwExceptionResponse(e.getMessage());
		}

		return responseData;
	}

	@RequestMapping(value = {"/v2"}, method = RequestMethod.POST)
	public Resources<UserDTO> saveUserv2(@Valid @RequestBody User requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		try {
			responseData = this.userServices.saveUser(requestData);
		} catch (Exception e) {
			responseData.throwExceptionResponse(e.getMessage());
		}

		return responseData;
	}

	@RequestMapping(value = {"/v1/all"}, method = RequestMethod.GET)
	public Resources<UserDTO> getUserAll(HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<UserDTO>();
		UserDTO userDomain = new UserDTO();

		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		responseData = this.userServices.getAllUser();

		responseData.throwSucceedResponse(userDomain);

		return responseData;
	}

	///NOTES : @PathVariable refering to {id}
	@RequestMapping(value = {"/v1/{id}"}, method = RequestMethod.GET)
	public Resources<UserDTO> getUserById(@PathVariable String id, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO userDomain = new UserDTO();

		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		try {
			int tempId = Integer.parseInt(id);
			responseData =  this.userServices.getUserById(tempId);

			servletResponse.setStatus(200);
		} catch (Exception e) {
			responseData.throwExceptionResponse(e.getMessage());
			servletResponse.setStatus(400);
		}

		return responseData;
	}

	@RequestMapping(value = {"/v1/{id}"}, method = RequestMethod.DELETE)
	public Resources<UserDTO> deleteUserById(@PathVariable String id, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();

		try {
			int tempId = Integer.parseInt(id);
			this.userServices.deleteUser(tempId);
			servletResponse.setStatus(200);
		} catch (Exception e) {
			responseData.throwExceptionResponse(e.getMessage());
			servletResponse.setStatus(400);
		}

		return responseData;
	}
}
