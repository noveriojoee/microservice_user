package com.research.template.Controllers;
import com.google.gson.Gson;
import com.research.template.DAO.UserDAO;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/users/v1"})
public class UserControllers {

	@Autowired
	UserDAO userDAO;

//	@RequestMapping(value= {"/login"},method = RequestMethod.POST)
//	public BaseClassDomain<UsersModel> loginAD(@RequestBody AuthenticationDTO requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
//		BaseClassDomain<UsersModel> responseData = null;
//
//	}

	@RequestMapping(value = {"/all"}, method = RequestMethod.GET)
	public Resources<UserDTO> getUserAll(HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO userDomain = new UserDTO();

		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		userDomain.setUsers(userDAO.findAll());

		responseData.setSucceedResponse(userDomain);

		return responseData;
	}

	///NOTES : @PathVariable refering to {id}
	@RequestMapping(value = {"/{id}"},method = RequestMethod.GET)
	public Resources<UserDTO> getUserById(@PathVariable String id, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = new Resources<>();
		UserDTO userDomain = new UserDTO();

		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		try{
			int tempId = Integer.parseInt(id);
			userDomain.setUser(userDAO.findById(tempId));

			responseData.setSucceedResponse(userDomain);
			servletResponse.setStatus(200);
		}catch(Exception e){
			responseData.throwExceptionResponse(e.getMessage());
			servletResponse.setStatus(400);
		}

		return responseData;
	}
}
