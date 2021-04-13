package com.guest.Controller;

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
import com.guest.Model.Guest;
import com.guest.Model.Show;
import com.guest.Service.GuestService;

@RestController
public class GuestController {
	
	@Autowired
	GuestService guestService;
	
	private static final Logger logger = LoggerFactory.getLogger(GuestController.class);
	
	@PostMapping("/addGuest")
	public Show addGuest(@RequestBody Guest guest)
	{
		logger.info("Inside addGuest() method of GuestController");
		return guestService.addGuest(guest);
	}
	
	@GetMapping("/getAllGuests")
	public List<Guest> getAllGuests()
	{
		logger.info("Inside getAllGuests() method of GuestController");
		return guestService.getAllGuests();
	}
	
	@GetMapping("/getGuestById/{id}")
	public Guest getGuestById(@PathVariable String id)
	{
		logger.info("Inside getGuestById() method of GuestController");
		return guestService.getGuestById(id);
	}
	
	@GetMapping("/getGuestByName/{name}")
	public List<Guest> getGuestByName(@PathVariable String name)
	{
		logger.info("Inside getGuestByName() method of GuestController");
		return guestService.getGuestByName(name);
	}
	
	/*
	@GetMapping("/getGuestByCity/{city}")
	public List<Guest> getGuestByCity(@PathVariable String city)
	{
		return guestService.getGuestByCity(city);
	}
	*/
	
	/*
	@GetMapping("/getGuestByStatus/{status}")
	public List<Guest> getGuestByStatus(@PathVariable String status)
	{
		logger.info("Inside getGuestByName() method of GuestController");
		return guestService.getAllGuestsByStatus(status);
	}
	*/
	
	@PutMapping("/updateGuest")
	public Show updateGuest(@RequestBody Guest guest)
	{
		logger.info("Inside updateGuest() method of GuestController");
		return guestService.updateGuest(guest);
	}
	
	@DeleteMapping("/deleteGuest/{id}")
	public Show delteGuest(@PathVariable String id)
	{
		logger.info("Inside delteGuest() method of GuestController");
		return guestService.deleteGuest(id);
	}
}
