package com.example.studentexample.util;

import java.util.List;

public class ErrorResponse {

	
	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}
	
	private String message;
	private List<String> details;
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the details
	 */
	public List<String> getDetails() {
		return details;
	}
	/**
	 * @param details the details to set
	 */
	public void setDetails(List<String> details) {
		this.details = details;
	}
	
	
}
