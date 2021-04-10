package com.room.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import com.room.Model.RoomDetails;
import com.room.Model.RoomType;
import com.room.Model.Show;
import com.room.Service.RoomTypeService;

@RestController
public class RoomTypeController {
	
	@Autowired
	RoomTypeService roomTypeService;
	
	@PostMapping("/addRoomType")
	public Show addNewRoomType(@RequestBody RoomDetails roomDetails) 
	{
		return roomTypeService.addNewType(roomDetails);	
	}
	
	@GetMapping("/getAllTypes")
	public RoomType getAllTypes() 
	{
		return roomTypeService.getAllTypes();
	}
	
	@DeleteMapping("/deleteRoomType/{type}")
	public Show delteRoomType(@PathVariable RoomType type)
	{
		return roomTypeService.deleteRoomType(type);
	}
}
