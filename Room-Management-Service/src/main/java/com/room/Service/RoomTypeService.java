package com.room.Service;

import java.util.HashMap;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.room.Model.RoomDetails;
import com.room.Model.RoomType;
import com.room.Model.Show;
import com.room.Repository.RoomTypeRepository;

@Service
public class RoomTypeService {
	
	@Autowired
	RoomTypeRepository roomTypeRepository;
	
	@Autowired
	RoomType roomType;
	
	
	@Value("${room.type.id}")
	private String roomTypeId;
	
	
	public Show addNewType(RoomDetails roomDetails) 
	{
		String type = roomDetails.getRoomType();
		long price = roomDetails.getPrice();
		
		try 
		{
			RoomType roomTypeInv = roomTypeRepository.findById(roomTypeId).get();
			HashMap<String,Long> roomTypes = roomTypeInv.getRoomType();
			roomTypes.put(type, price);
			roomTypeInv.setRoomType(roomTypes);
			roomTypeRepository.save(roomTypeInv);
			
		}
		catch(NoSuchElementException e) 
		{
			HashMap<String, Long> roomTypes = new HashMap<String, Long>();
			roomTypes.put(type, price);
			roomType.setId(roomTypeId);
			roomType.setRoomType(roomTypes);
			roomTypeRepository.save(roomType);
			
		}
		return  new Show("Success!, New Room of Type - "+type," and Price- "+price+ " Added");	
	}
	
	public RoomType getAllTypes()
	{
		try
		{
			return roomTypeRepository.findById(roomTypeId).get();
		}
		catch(Exception e)
		{
			HashMap<String, Long> gr = new HashMap<String, Long>();
			return new RoomType("Room Type Not Found",gr);
		}
	}
	
	public Show deleteRoomType(RoomType roomType)
	{
	 roomTypeRepository.delete(roomType);
	 return new Show("Success!, Room type"+roomType, " Deleted");
	}
}
