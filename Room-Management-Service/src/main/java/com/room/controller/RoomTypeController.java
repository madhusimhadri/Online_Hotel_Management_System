package com.room.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.room.model.Show;
import com.room.model.RoomDetails;
import com.room.model.RoomType;
import com.room.service.RoomTypeService;



@RestController
public class RoomTypeController {
	
	@Autowired
	RoomTypeService roomTypeInventoryService;
	
	@PostMapping("/addRoomType")
	public Show addNewRoomType(@RequestBody RoomDetails roomDetails) {
		return roomTypeInventoryService.addNewType(roomDetails);	
	}
	
	@GetMapping("/getAllTypes")
	public RoomType getAllTypes() {
		return roomTypeInventoryService.getAllTypes();
	}
	
	@DeleteMapping("/deleteRoomType/{type}")
	public Show deleteRoomType(@PathVariable String type) {
		return roomTypeInventoryService.deleteByKey(type);
	}
}
