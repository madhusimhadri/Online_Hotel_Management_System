package com.hotel.Model;

public class BookingData {
	
	private Guest guest;
	private Room room;
	
	public BookingData()
	{
		
	}

	public BookingData(Guest guest, Room room) {
		super();
		this.guest = guest;
		this.room = room;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	
	
}
