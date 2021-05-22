package com.research.template.Controllers;
import com.google.gson.Gson;
import com.research.template.Domain.DTO.UserDTO;
import com.research.template.Domain.Resources;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/users/v1"})
public class UserControllers {

//	@RequestMapping(value= {"/login"},method = RequestMethod.POST)
//	public BaseClassDomain<UsersModel> loginAD(@RequestBody AuthenticationDTO requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
//		BaseClassDomain<UsersModel> responseData = null;
//
//	}

	@RequestMapping(value= {"/"},method = RequestMethod.GET)
	public Resources<UserDTO> getListProducts(HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
		Resources<UserDTO> responseData = null;
		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();


		return responseData;
	}

}
