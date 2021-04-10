package com.room.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class BookingDetails {
	
	@JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	private Date dateOfBooking;
	private int noOfNights;
	private String guestId;
	
	public BookingDetails() {
		
	}

	public BookingDetails(Date dateOfBooking, int noOfNights, String guestId) {
		super();
		this.dateOfBooking = dateOfBooking;
		this.noOfNights = noOfNights;
		this.guestId = guestId;
	}

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public int getNoOfNights() {
		return noOfNights;
	}

	public void setNoOfNights(int noOfNights) {
		this.noOfNights = noOfNights;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	
	
	
	/*public BookingDetails(Date bookingDate, int nights, String guestId) {
		super();
		this.dateOfBooking = bookingDate;
		this.noOfNights = nights;
		this.guestId = guestId;
	}
	public Date getBookingDate() {
		return dateOfBooking;
	}
	public void setBookingDate(Date bookingDate) {
		this.dateOfBooking = bookingDate;
	}
	public int getNights() {
		return noOfNights;
	}
	public void setNights(int nights) {
		this.noOfNights = nights;
	}
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
	}
	*/
	
}
