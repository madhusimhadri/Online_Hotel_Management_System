package com.employee.Model;

public class Show {
	
	private String referId;
	private String message;
	
	// Default
	public Show()
	{
		
	}

	public Show(String referId, String message) {
		super();
		this.referId = referId;
		this.message = message;
	}

	public String getReferId() {
		return referId;
	}

	public void setReferId(String referId) {
		this.referId = referId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
