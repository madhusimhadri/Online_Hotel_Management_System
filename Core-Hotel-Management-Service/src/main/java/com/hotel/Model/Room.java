package com.hotel.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RoomInventory")
public class Room {
	@Id
	private String id;
	private String roomType;
	private String status;
	private BookingDetails details;
	
	// Default
	public Room() {
		
	}
	
	// for status - Available
	public Room(String id, String roomType, String status) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.status = status;
	}
	
	// for status- Booked
	public Room(String id, String roomType, String status, BookingDetails details) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.status = status;
		this.details = details;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BookingDetails getBookingDetails() {
		return details;
	}

	public void setBookingDetails(BookingDetails details) {
		this.details = details;
	}
	
}
