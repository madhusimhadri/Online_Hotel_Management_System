package com.room.model;

import java.util.Date;

public class BookingDetails {
	private Date bookingDate;
	private int nights;
	private String guestId;
	public BookingDetails() {
		
	}
	public BookingDetails(Date bookingDate, int nights, String guestId) {
		super();
		this.bookingDate = bookingDate;
		this.nights = nights;
		this.guestId = guestId;
	}
	public Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public int getNights() {
		return nights;
	}
	public void setNights(int nights) {
		this.nights = nights;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	
}
