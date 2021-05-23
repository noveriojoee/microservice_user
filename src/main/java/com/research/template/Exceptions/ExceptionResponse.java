package com.research.template.Exceptions;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamps;
	private String message;
	private String details;


	public ExceptionResponse(Date timestamps, String messages, String details){
		super();
		this.timestamps = timestamps;
		this.message = messages;
		this.details = details;
	}
	public Date getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(Date timestamps) {
		this.timestamps = timestamps;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
}
