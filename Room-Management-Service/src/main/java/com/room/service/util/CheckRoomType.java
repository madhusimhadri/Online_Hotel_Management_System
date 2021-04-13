package com.room.service.util;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.room.exception.RoomTypeNotFound;
import com.room.model.RoomType;
import com.room.repository.RoomTypeRepository;


@Service
public class CheckRoomType{
	@Autowired
	private RoomTypeRepository roomType;
	
	@Value("${room.type.id}")
	private String roomTypeId;
	
	public boolean exists(String type) throws RoomTypeNotFound {
		
		RoomType roomTypeObject = roomType.findById(roomTypeId).get();
		HashMap<String, Long> availableTypes = roomTypeObject.getRoomType();
		boolean containsKey = availableTypes.containsKey(type);
		if(containsKey == false) {
			throw new RoomTypeNotFound("Room type doesn't exist,Add first to continue");
		}
		return containsKey;
	}
}
