package com.research.template.Domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resources<T>{
	private String responseCode;
	private String responseMessage;
	private T responseObject;


	public void throwSucceedResponse(T responseObject){
		this.responseCode = "200";
		this.responseMessage = "OK";
		this.responseObject = responseObject;
	}

	public void throwExceptionResponse(String responseMessage) {
		this.responseCode = "400";
		this.responseMessage = responseMessage;
		this.responseObject = null;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public T getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(T responseObject) {
		this.responseObject = responseObject;
	}
}
