package com.room.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.room.Model.Report;
import com.room.Model.Room;
import com.room.Model.RoomsAvailable;
import com.room.Model.RoomsBooked;
import com.room.Model.Show;
import com.room.Service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	private static final Logger  logger = LoggerFactory.getLogger(RoomController.class);
	
	@PostMapping("/addRoom")
	public Show addNewRoom(@RequestBody Room room) 
	{	
		logger.info("Inside addNewRoom() method of RoomController");
		return roomService.addNewRoom(room);
	}
	
	@PostMapping("/addMultiple/{type}/{count}")
	public Show addMultipleRooms(@PathVariable int count, @PathVariable String type) 
	{
		logger.info("Inside addMultipleRooms() method of RoomController");
		return roomService.addMultipleRooms(count, type);
	}
	
	@GetMapping("/getRoom/{id}")
	public Room getRoomById(@PathVariable String id) 
	{	
		logger.info("Inside getRoomById() method of RoomController");
		return roomService.getRoomById(id);
	}
	
	@GetMapping("/getAllRoomsByType/{type}")
	public List<Room> getRoomsByType(@PathVariable String type)
	{
		logger.info("Inside getRoomsByType() method of RoomController");
		return roomService.getRoomsByType(type);
	}
	
	@GetMapping("/getAllRoomsByStatus/{status}")
	public List<Room> getRoomsByStatus(@PathVariable String status)
	{
		logger.info("Inside getRoomsByStatus() method of RoomController");
		return roomService.getRoomsByStatus(status);
	}
	
	@GetMapping("/getAllAvailableRooms")
	public RoomsAvailable getAllAvailableRooms() 
	{
		logger.info("Inside getAllAvailableRooms() method of RoomController");
		return roomService.getAllAvailableRooms();
	}
	
	@GetMapping("/getAllBookedRooms")
	public RoomsBooked getAllBookedRooms() 
	{
		logger.info("Inside getAllBookedRooms() method of RoomController");
		return roomService.getAllBookedRooms();
	}
	
	@PutMapping("/updateRoom")
	public Show updateRoomDetails(@RequestBody Room room) 
	{
		logger.info("Inside updateRoomDetails() method of RoomController");
		return roomService.updateRoomDetails(room);
	}
	
	@DeleteMapping("/deleteRoom/{id}")
	public Show deleteRoomById(@PathVariable String id)
	{
		logger.info("Inside deleteRoomById() method of RoomController");
		return roomService.deleteRoomById(id);
	}
	
	@GetMapping("/getReports")
	public Report getReports() 
	{
		logger.info("Inside getreports() method of RoomController");
		return roomService.generateEarningsReport();
	}
}
