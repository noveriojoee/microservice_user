package com.research.template.Domain;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resources<T> {
	private String responseCode;
	private String responseMessage;
	private T responseObject;
}
