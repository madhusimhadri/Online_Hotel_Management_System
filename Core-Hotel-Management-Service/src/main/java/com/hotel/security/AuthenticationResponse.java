package com.hotel.security;

import com.hotel.model.Employee;

public class AuthenticationResponse {
	
	private String jwt;
	private Employee employee;
	private String message;
	private boolean status;
	
	public AuthenticationResponse()
	{
		
	}

	public AuthenticationResponse(String jwt, Employee employee, String message, boolean status) {
		super();
		this.jwt = jwt;
		this.employee = employee;
		this.message = message;
		this.status = status;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
