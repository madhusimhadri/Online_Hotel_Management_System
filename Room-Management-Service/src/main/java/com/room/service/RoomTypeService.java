package com.room.service;

import java.util.HashMap;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.room.model.Show;
import com.room.model.RoomDetails;
import com.room.model.RoomType;
import com.room.repository.RoomTypeRepository;


@Service
public class RoomTypeService {
	
	@Autowired
	RoomTypeRepository roomTypeDao;
	
	@Autowired
	RoomType roomTypeInventory;
	
	@Autowired
	Logger LOGGER;
	
	@Value("${room.type.id}")
	private String roomTypeId;
	
	// Add New Room Type - SINGLE,QUAD,KING...
	public Show addNewType(RoomDetails roomDetails) {
		
		String type = roomDetails.getType();
		long price = roomDetails.getPrice();
		
		try {
			RoomType roomTypeInv = roomTypeDao.findById(roomTypeId).get();
			HashMap<String,Long> roomTypes = roomTypeInv.getRoomType();
			roomTypes.put(type, price);
			roomTypeInv.setRoomType(roomTypes);
			roomTypeDao.save(roomTypeInv);
			
		}
		catch(NoSuchElementException e) {
			LOGGER.warn(e.getMessage());
			HashMap<String, Long> roomTypes = new HashMap<String, Long>();
			roomTypes.put(type, price);
			roomTypeInventory.setId(roomTypeId);
			roomTypeInventory.setRoomType(roomTypes);
			roomTypeDao.save(roomTypeInventory);
			
		}
		return new Show("Success! Room of Type "+type+" and Price "+price," Added");
		
	}
	
	//Get all types
	public RoomType getAllTypes() {
		try {
			return roomTypeDao.findById(roomTypeId).get();
		}catch(Exception e){
			
			HashMap<String, Long> gt = new HashMap<String, Long>();
			LOGGER.warn(e.getMessage());
			return new RoomType("Type doesn't exist!",gt);
		}
	}
	
	//Delete Room Type
	public Show deleteByKey(String roomType) {
		try {
			RoomType roomTypeInv = roomTypeDao.findById(roomTypeId).get();
			HashMap<String, Long> allTypes = roomTypeInv.getRoomType();
			if(!allTypes.containsKey(roomType)) {
				throw new NoSuchElementException();
			}
			allTypes.remove(roomType);
			roomTypeInv.setRoomType(allTypes);
			roomTypeDao.save(roomTypeInv);
			return new Show(roomType,"Deleted Successfully!");
		}catch(NoSuchElementException e) {
			LOGGER.warn(e.getMessage());
			return new Show("Invalid Request!","Room Doesn't Exist");
		}
	}
}
