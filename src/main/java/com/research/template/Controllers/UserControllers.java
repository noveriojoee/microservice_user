package com.research.template.Controllers;

import com.google.gson.Gson;
import com.research.template.DAO.UserDAO;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;
import com.research.template.Domain.Bean.User;
import com.research.template.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/users/v1"})
public class UserControllers {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(method = RequestMethod.POST)
	public Resources<UserDTO> saveUser(@RequestBody User requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest){
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO dto = new UserDTO();
		try{
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();
			Set<ConstraintViolation<User>> violations = validator.validate(requestData);
			Optional<ConstraintViolation<User>> validatedObject = violations.stream().findFirst();
			if (validatedObject.isEmpty()){
				dto.setUser(userDAO.addUser(requestData.getName()));
				responseData.throwSucceedResponse(dto);
			}else{
				responseData.throwResponseWithCode("405","Bad Request "+validatedObject.get().getMessage(),null);
			}
		}catch(Exception e){
			responseData.throwExceptionResponse(e.getMessage());
		}

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
