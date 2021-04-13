package com.employee.model;

public class ContactDetails {
	
	private String email;
	private String country;
	private String state;
	private String city;
	private String street;
	private String zipcode;
	private long mobileNo;
	
	//Default Constructor
	public ContactDetails()
	{
		
	}
	
	public ContactDetails(String email, String country, String state, String city, String street, String zipcode,
			long mobileNo) {
		super();
		this.email = email;
		this.country = country;
		this.state = state;
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
		this.mobileNo = mobileNo;
	}

	//Getters and Setters
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	
	
	
	
	
	
}
