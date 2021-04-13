package com.room.model;

import java.util.List;

public class Report {
	private int totalRooms;
	private int availableRooms;
	private int bookedRooms;
	private List<BookedRoomsDetails> bookedRoomsDetails;
	private long totalEarnings;
	public Report() {
		
	}
	public Report(int totalRooms, int availableRooms, int bookedRooms, List<BookedRoomsDetails> bookedRoomsDetails,
			long totalEarnings) {
		super();
		this.totalRooms = totalRooms;
		this.availableRooms = availableRooms;
		this.bookedRooms = bookedRooms;
		this.bookedRoomsDetails = bookedRoomsDetails;
		this.totalEarnings = totalEarnings;
	}
	public int getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}
	public int getAvailableRooms() {
		return availableRooms;
	}
	public void setAvailableRooms(int availableRooms) {
		this.availableRooms = availableRooms;
	}
	public int getBookedRooms() {
		return bookedRooms;
	}
	public void setBookedRooms(int bookedRooms) {
		this.bookedRooms = bookedRooms;
	}
	public List<BookedRoomsDetails> getBookedRoomsDetails() {
		return bookedRoomsDetails;
	}
	public void setBookedRoomsDetails(List<BookedRoomsDetails> bookedRoomsDetails) {
		this.bookedRoomsDetails = bookedRoomsDetails;
	}
	public long getTotalEarnings() {
		return totalEarnings;
	}
	public void setTotalEarnings(long totalEarnings) {
		this.totalEarnings = totalEarnings;
	}
	
	
	
}
