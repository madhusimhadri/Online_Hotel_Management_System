package com.hotel.model;

public class RoomDetails {
	private String type;
	private long price;
	public RoomDetails() {
		
	}
	public RoomDetails(String type, long price) {
		super();
		this.type = type;
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
}
