package com.hotel.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.Model.BookingData;
import com.hotel.Model.Guest;
import com.hotel.Model.Invoice;
import com.hotel.Model.Room;
import com.hotel.Model.RoomStay;
import com.hotel.Model.RoomType;
import com.hotel.Model.Show;

@Service
public class ReceptionistService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	Logger logger;
	
	public String roomApi = "room-management-service";

	// ************************* API Calls To Room Management Service ********************* //

	public String ADD_ROOM = "http://" + roomApi + "/addRoom";
	public String ADD_MULTIPLE_ROOMS = "http://" + roomApi + "/addMultiple/{type}/{count}";
	public String GET_ROOM_BY_ID = "http://" + roomApi + "/getRoom/{id}";
	public String GET_ROOM_BY_TYPE = "http://" + roomApi + "/getAllRoomsByType/{type}";
	public String GET_ROOM_BY_STATUS = "http://" + roomApi + "/getAllRoomsByStatus/{status}";
	public String GET_ALL_AVAILABLE_ROOMS = "http://" + roomApi + "/getAllAvailableRooms";
	public String GET_ALL_BOOKED_ROOMS = "http://" + roomApi + "/getAllBookedRooms";
	public String UPDATE_ROOM = "http://" + roomApi + "/updateRoom";
	public String DELETE_ROOM = "http://" + roomApi + "/deleteRoom/{id}";
	public String ADD_NEWROOM_TYPE = "http://" + roomApi + "/addRoomType";
	public String GET_ALL_ROOMTYPES = "http://" + roomApi + "/getAllTypes";
	public String DELETE_ROOMTYPE = "http://" + roomApi + "/deleteRoomType/{type}";
	public String GET_REPORT = "http://" + roomApi + "/getReports";

	// ************************* API Calls To Room Management Service Ends Here *************** //

	public String employeeApi = "employee-management-service";

	// ************************* API Calls to Employee Management Service ********************* //

	public String ADD_EMPLOYEE = "http://" + employeeApi + "/addEmployee";
	public String GET_ALL_EMPLOYEES = "http://" + employeeApi + "/getAllEmployees";
	public String GET_EMPLOYEE_BY_ID = "http://" + employeeApi + "/getEmployeeById/{id}";
	public String GET_EMPLOYEE_BY_NAME = "http://" + employeeApi + "/getEmployeeByName/{name}";
	public String GET_EMPLOYEE_BY_ROLE = "http://" + employeeApi + "/getEmployeeByRole/{role}";
	public String UPDATE_EMPLOYEE = "http://" + employeeApi + "/updateEmployee";
	public String DELETE_EMPLOYEE = "http://" + employeeApi + "/deleteEmployee/{id}";

	// ************************* API Calls to Employee Management Service Ends Here *********** //

	public String guestApi = "guest-management-service";

	// ************************* API Calls to Guest Management Service ************************ // 

	public String ADD_GUEST = "http://" + guestApi + "/addGuest";
	public String GET_ALL_GUESTS = "http://" + guestApi + "/getAllGuests";
	public String GET_GUEST_BY_ID = "http://" + guestApi + "/getGuestById/{id}";
	public String GET_GUEST_BY_NAME = "http://" + guestApi + "/getGuestByName/{name}";
	public String GET_GUEST_BY_STATUS = "http://" + guestApi + "/getGuestByStatus/{status}";
	public String UPDATE_GUEST = "http://" + guestApi + "/updateGuest";
	public String DELETE_GUEST = "http://" + guestApi + "/deleteGuest/{id}";
	
	// ************************* API Calls to Guest Management Service Ends Here ************** //
	
	@Value("${invoice.heading.company.name}")
	private String invoiceCompanyName;
	
	@Value("${invoice.tax.percent}")
	private int invoiceTax;
	
	// ====> Receptionist Operations on GUEST
	
	// Add Guest
	public Show addGuest(Guest guest)
	{
		Show result = restTemplate.postForObject(ADD_GUEST, guest, Show.class);
		return result;
	}
	
	// Get Guest By Id
	public Guest getGuestById(String id)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_GUEST_BY_ID;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return restTemplate.exchange(url, HttpMethod.GET,httpEntity,Guest.class,id).getBody();
	}
	
	// Get Guest By Name
	public List<Guest> getGuestByName(String name)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_GUEST_BY_NAME;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Guest[]> guests = restTemplate.exchange(url,HttpMethod.GET,httpEntity,Guest[].class,name);
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.stream(guests.getBody()).map(g->mapper.convertValue(g,Guest.class)).collect(Collectors.toList());
	}
	
	// Update Guest Details
	public Show updateGuestDetails(Guest guest)
	{
		if(guest.getId() == null) 
		{
			return new Show("Invalid Id!"," Enter a Proper Id");
		}
		
		RequestEntity<Guest> requestEntity = RequestEntity.put(UPDATE_GUEST).accept(MediaType.APPLICATION_JSON).body(guest);
		ResponseEntity<Show> result = restTemplate.exchange(requestEntity, Show.class);
		return result.getBody();
	}
	
	// Delete Guest By Id
	public Show deleteGuest(String id)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = DELETE_GUEST;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Show> result = restTemplate.exchange(url, HttpMethod.DELETE,httpEntity,Show.class,id);
		return result.getBody();	
	}
	
	// ====> Receptionist Operations on ROOM
	
	// Get Room By Id
	public Room getRoomById(String id)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_ROOM_BY_ID;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return restTemplate.exchange(url,HttpMethod.GET,httpEntity,Room.class,id).getBody();	
	}
	
	// Get Room By Status
	public List<Room> getRoomsByStatus(String status)
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_ROOM_BY_STATUS;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		ResponseEntity<Room[]> rooms = restTemplate.exchange(url,HttpMethod.GET,httpEntity,Room[].class,status);
		ObjectMapper mapper = new ObjectMapper();
		return Arrays.stream(rooms.getBody()).map(r->mapper.convertValue(r,Room.class)).collect(Collectors.toList());
	}
	
	// Update Room Details
	public Show updateRoomDetails(Room room)
	{
		if(room.getId()==null)
		{
			return new Show("Invalid Request!","Enter a proper Id");
		}
		
		RequestEntity<Room> requestEntity = RequestEntity.put(UPDATE_ROOM).accept(MediaType.APPLICATION_JSON).body(room);
		ResponseEntity<Show> result = restTemplate.exchange(requestEntity, Show.class);
		return result.getBody();
		
	}
	
	// Make Reservations
	// Update Room and Guest Details
	public Show makeReservation(BookingData bookingData)
	{
		Room room = bookingData.getRoom();
		Guest guest = bookingData.getGuest();

		Show updateGuestDetails = updateGuestDetails(guest);
		Show updateRoomDetails = updateRoomDetails(room);
		
		if(updateGuestDetails.getReferId().contains("Invalid") || updateRoomDetails.getReferId().contains("Invalid"))
		{
			return new Show("Invalid Request!",updateGuestDetails.getMessage()+" "+updateRoomDetails.getMessage());
		}
		return new Show(room.getId(),"Room booked for guest with name "+guest.getName()+" with Id "+guest.getId());	
	}
	
	// Get All Room Types - Used in generating invoice
	public RoomType getAllRoomTypes()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String url = GET_ALL_ROOMTYPES;
		
		HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		return restTemplate.exchange(url,HttpMethod.GET,httpEntity,RoomType.class).getBody();
		
	}
	
	//Get Tax - Used in generating invoice
	public float getTax(int days,long price)
	{
		long total = Long.valueOf(days)* price;
		float tax = (float) invoiceTax * (float) total/100;
		return tax;
	}
	
	// Issue Bill
	public Invoice issueBill(String guestId,String employeeName)
	{
		Guest guest = getGuestById(guestId);
		if(guest.getRoomDetails() == null)
		{
			logger.error("No record found for guest");
			return null;
		}
		
		RoomStay roomDetails = guest.getRoomDetails();
		Room room = getRoomById(roomDetails.getRoomId());
		
		Invoice invoice = new Invoice();
		invoice.setCompanyName(invoiceCompanyName);
		invoice.setGuestId(guestId);
		invoice.setGuestName(guest.getName());
		invoice.setGuestMobile(guest.getMobileNo());
		
		int numberOfPeople = guest.getMemberDetails().size()+1;
		
		invoice.setNumberOfPeople(numberOfPeople);
		invoice.setBookedRoomType(room.getRoomType());
		
		RoomType allRoomTypeObjs = getAllRoomTypes();
		HashMap<String,Long> allRoomTypes = allRoomTypeObjs.getRoomType();
		Long roomPrice = allRoomTypes.get(room.getRoomType());
		
		invoice.setRoomPricePerNight(roomPrice);
		invoice.setNights(roomDetails.getNoOfNights());
		invoice.setBookingDate(room.getBookingDetails().getDateOfBooking());
		
		float tax = getTax(roomDetails.getNoOfNights(),roomPrice);
		invoice.setTax(tax);
		float totalBill = (float) room.getBookingDetails().getNoOfNights() * (float) roomPrice + tax;
		invoice.setTotalBill(totalBill);
		
		invoice.setIssuedBy(employeeName);
		
		return invoice;
	}
	
}
