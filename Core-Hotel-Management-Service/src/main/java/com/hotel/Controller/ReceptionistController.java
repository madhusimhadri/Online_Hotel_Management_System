package com.hotel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.Model.BookingData;
import com.hotel.Model.Guest;
import com.hotel.Model.Invoice;
import com.hotel.Model.Room;
import com.hotel.Model.Show;
import com.hotel.Service.ReceptionistService;

@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
	
	@Autowired
	ReceptionistService receptionistService;
	
	/* Receptionist Operations
	 * Operations on Room
	 * Operations on Guest
	 * Generating Bill
	 */
	
	// ************************* Receptionist Operations ************************** // 
	
	// Add Guest
	@PostMapping("/addGuest")
	public Show addGuest(@RequestBody Guest guest)
	{
		return receptionistService.addGuest(guest);
	}
	
	// Get Guest By Id
	@GetMapping("/getGuestById/{id}")
	public Guest getGuestById(@PathVariable String id)
	{
		return receptionistService.getGuestById(id);
	}
	
	// Get Guest By Name
	@GetMapping("/getGuestByName/{name}")
	public List<Guest> getGuestByName(@PathVariable String name)
	{
		return receptionistService.getGuestByName(name);
	}
	
	// Update Guest Details
	@PutMapping("/updateGuest")
	public Show updateGuestDetails(@RequestBody Guest guest)
	{
		return receptionistService.updateGuestDetails(guest);
	}
	
	// Delete Guest
	@DeleteMapping("/deleteGuest/{id}")
	public Show deleteGuest(@PathVariable String id)
	{
		return receptionistService.deleteGuest(id);
	}
	
	// Get Room By Id
	@GetMapping("/getRoom/{id}")
	public Room getRoomById(@PathVariable String id)
	{
		return receptionistService.getRoomById(id);
	}
	
	// Get Rooms By Status - Available/ Booked
	@GetMapping("/getAllRoomsByStatus/{status}")
	public List<Room> getRoomsByStatus(@PathVariable String status)
	{
		return receptionistService.getRoomsByStatus(status);
	}
	
	// Make Reservations
	@PutMapping("/makeReservation")
	public Show makeReservation(@RequestBody BookingData bookingData)
	{
		return receptionistService.makeReservation(bookingData);
	}
	
	// Issue Bill
	@GetMapping("/issueBill/{guestId}")
	public Invoice issueBills(@PathVariable String guestId,@RequestParam String employeeName)
	{
		return receptionistService.issueBill(guestId, employeeName);
	}
	
	
}
