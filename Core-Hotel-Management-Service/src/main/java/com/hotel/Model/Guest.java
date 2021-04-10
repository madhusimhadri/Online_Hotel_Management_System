package com.hotel.Model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "GuestInventory")
public class Guest 
{
	@Id
	private String id;
	private String name;
	private String gender;
	private int age;
	private String mobileNo;
	
	
	// total number of people = guest + addition members
	private List<AdditionalMembers> memberDetails;
	private Address address;
	
	// currentStatus - check in/check out
	private String currentStatus;
	
	// Room details of the guest
	private RoomStay roomDetails;
	
	// Default
	public Guest()
	{
		
	}
	
	//Constructor is used for post method  since Id is automatically generated
	public Guest(String name, String gender, int age, String mobileNo, List<AdditionalMembers> memberDetails,
			Address address, String currentStatus, RoomStay roomDetails) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.memberDetails = memberDetails;
		this.address = address;
		this.currentStatus = currentStatus;
		this.roomDetails = roomDetails;
	}

	//Constructor is used for put method since guest is updated using id 
	public Guest(String id, String name, String gender, int age, String mobileNo,
			List<AdditionalMembers> memberDetails, Address address, String currentStatus, RoomStay roomDetails) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.mobileNo = mobileNo;
		this.memberDetails = memberDetails;
		this.address = address;
		this.currentStatus = currentStatus;
		this.roomDetails = roomDetails;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public List<AdditionalMembers> getMemberDetails() {
		return memberDetails;
	}

	public void setMemberDetails(List<AdditionalMembers> memberDetails) {
		this.memberDetails = memberDetails;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public RoomStay getRoomDetails() {
		return roomDetails;
	}

	public void setRoomDetails(RoomStay roomDetails) {
		this.roomDetails = roomDetails;
	}

	@Override
	public String toString() {
		return "Guest [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + ", mobileNo=" + mobileNo
				+ ", memberDetails=" + memberDetails + ", address=" + address + ", currentStatus=" + currentStatus
				+ ", roomDetails=" + roomDetails + "]";
	}
	
	
	
	
}
