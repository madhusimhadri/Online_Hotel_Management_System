package com.hotel.Model;


import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "RoomTypeInventory")
public class RoomType {
	
	@Id
	private String id; 
	
	private HashMap<String,Long> roomType;
	
	public RoomType() {
		
	}
	public RoomType(String id, HashMap<String, Long> roomType) {
		super();
		this.id = id;
		this.roomType = roomType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public HashMap<String, Long> getRoomType() {
		return roomType;
	}
	public void setRoomType(HashMap<String, Long> roomType) {
		this.roomType = roomType;
	}
	
}
