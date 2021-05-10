package com.hotel.model;

import java.util.List;

public class RoomsAvailable {
	private int count;
	private List<Room> availableRooms;
	
	public RoomsAvailable() {
		
	}
	public RoomsAvailable(int count, List<Room> availableRooms) {
		super();
		this.count = count;
		this.availableRooms = availableRooms;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Room> getAvailableRooms() {
		return availableRooms;
	}

	public void setAvailableRooms(List<Room> availableRooms) {
		this.availableRooms = availableRooms;
	}
}
