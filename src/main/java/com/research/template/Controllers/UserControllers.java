package com.research.template.Controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = {"/users/v1"})
public class UserControllers {

//	@RequestMapping(value= {"/login"},method = RequestMethod.POST)
//	public BaseClassDomain<UsersModel> loginAD(@RequestBody AuthenticationDTO requestData, HttpServletResponse servletResponse, HttpServletRequest servletRequest) {
//		BaseClassDomain<UsersModel> responseData = null;
//
//	}

	@RequestMapping(value= {"/get"},method = RequestMethod.GET)
	public BaseClassDomain<GetListProductsActivityDTO> getListProducts(HttpServletResponse servletResponse,HttpServletRequest servletRequest) {
		BaseClassDomain<GetListProductsActivityDTO> responseData = null;
		Gson json = new Gson();
		UUID ioGuid = UUID.randomUUID();

		logger.info((servletRequest.getServletPath() + " : [ID]").replace("[ID]", ioGuid.toString()) + " | "
				+ ApplicationConstant.LOG_FLAG_REQUEST.replace("[string]", ""));

		responseData = this.productActivityServices.getListProduct();

		logger.info((servletRequest.getServletPath() + " : [ID]").replace("[ID]", ioGuid.toString()) + " | "
				+ ApplicationConstant.LOG_FLAG_RESPONSE.replace("[string]", json.toJson(responseData)));


		return responseData;
	}

}
