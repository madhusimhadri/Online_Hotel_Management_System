package com.hotel.model;

public class BillDetails {
	String guestId;
	String employeeName;
	
	public BillDetails() {
		
	}
	

	public BillDetails(String guestId, String employeeName) {
		super();
		this.guestId = guestId;
		this.employeeName = employeeName;
	}


	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	

}