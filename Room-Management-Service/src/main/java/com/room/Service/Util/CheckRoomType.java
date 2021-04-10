package com.room.Service.Util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.room.Exception.RoomTypeNotFound;
import com.room.Model.RoomType;
import com.room.Repository.RoomTypeRepository;

@Service
public class CheckRoomType{
	@Autowired
	private RoomTypeRepository roomType;
	
	@Value("${room.type.id}")
	private String roomTypeId;
	
	public boolean exists(String type) throws RoomTypeNotFound 
	{
		
		RoomType roomTypeObject = roomType.findById(roomTypeId).get();
		HashMap<String, Long> availableTypes = roomTypeObject.getRoomType();
		boolean containsKey = availableTypes.containsKey(type);
		
		if(containsKey == false) 
		{
			throw new RoomTypeNotFound("Room type doesn't exist, consider adding first");
		}
		return containsKey;
	}
}
