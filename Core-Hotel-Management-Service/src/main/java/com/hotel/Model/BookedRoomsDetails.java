package com.hotel.Model;

import java.util.Date;

public class BookedRoomsDetails {
	private String id;
	private String status;
	private Date dateOfBooking;
	private int noOfNights;
	private long price;
	
	public BookedRoomsDetails() {
		
	}
	public BookedRoomsDetails(String id, String status, Date dateOfBooking, int nights, long amount) {
		super();
		this.id = id;
		this.status = status;
		this.dateOfBooking = dateOfBooking;
		this.noOfNights = nights;
		this.price = amount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	public int getNights() {
		return noOfNights;
	}
	public void setNights(int nights) {
		this.noOfNights = nights;
	}
	public long getAmount() {
		return price;
	}
	public void setAmount(long amount) {
		this.price = amount;
	}
	
}
