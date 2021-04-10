package com.room.Model;

public class RoomDetails {
	
	private String roomType;
	private long price;
	
	public RoomDetails()
	{
		
	}

	public RoomDetails(String roomType, long price) {
		super();
		this.roomType = roomType;
		this.price = price;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	
	
	
	
}
