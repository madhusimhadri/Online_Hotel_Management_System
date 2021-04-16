package com.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.BookingData;
import com.hotel.model.Employee;
import com.hotel.model.Guest;
import com.hotel.model.PaymentData;
import com.hotel.model.Room;
import com.hotel.model.Show;
import com.hotel.service.PaymentService;
import com.hotel.service.ReceptionistService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/receptionist")
public class ReceptionistController {
	
	@Autowired
	ReceptionistService receptionistService;
	
	@Autowired
	PaymentService paymentService;
	
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
	
	//Get Logged in Employee
	@GetMapping("/loggedInEmployee")
	public Employee getLoggedInEmployee()
	{
		return receptionistService.getLoggedInEmployee();
	}
	
	// Make Reservations
	@PutMapping("/makeReservation")
	public Show makeReservation(@RequestBody BookingData bookingData)
	{
		System.out.println(bookingData.getRoom());
		return receptionistService.makeReservation(bookingData);
	}
	
	// Issue Bill
	@GetMapping("/issueBill/{guestId}")
	public Show issueBills(@PathVariable String guestId,@RequestParam String employeeName)
	{
		return receptionistService.issueBill(guestId, employeeName);
	}
	
	// Get Invoice By Id
	@GetMapping("/getInvoiceById/{id}")
	public PaymentData getInvoiceById(@PathVariable String id)
	{
		return paymentService.getInvoiceById(id);
	}
	
	// Get Invoice By Status
	@GetMapping("/getInvoiceByStatus/{status}")
	public List<PaymentData> getInvoiceByStatus(@PathVariable String status)
	{
		return paymentService.getInvoiceByStatus(status);
	}
	
	// Get All Invoices
	@GetMapping("/getAllInvoices")
	public List<PaymentData> getAllInvoices()
	{
		return paymentService.getAllInvoices();
	}
	
	// Update Status
	@PutMapping("/updateInvoiceStatus")
	public Show updateStatus(@RequestBody PaymentData paymentData)
	{
		return paymentService.updateInvoiceStatus(paymentData);
	}
	
	// Delete Invoice
	@DeleteMapping("/deleteInvoiceById/{id}")
	public Show deleteInvoiceById(@PathVariable String id)
	{
		return paymentService.deleteInvoiceById(id);
	}
	
	
	
	
	
	
	
	
	
}
