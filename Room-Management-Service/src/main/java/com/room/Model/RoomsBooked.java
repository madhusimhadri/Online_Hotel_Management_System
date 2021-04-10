package com.room.Model;

import java.util.List;

public class RoomsBooked {
	private int count;
	private List<Room> bookedRooms;
	public RoomsBooked() {
		
	}
	public RoomsBooked(int count, List<Room> bookedRooms) {
		super();
		this.count = count;
		this.bookedRooms = bookedRooms;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Room> getBookedRooms() {
		return bookedRooms;
	}
	public void setBookedRooms(List<Room> bookedRooms) {
		this.bookedRooms = bookedRooms;
	}
	
}
