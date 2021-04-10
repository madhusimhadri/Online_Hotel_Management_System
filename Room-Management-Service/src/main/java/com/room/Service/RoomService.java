package com.room.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.room.Exception.RoomStatusException;
import com.room.Model.BookedRoomsDetails;
import com.room.Model.Report;
import com.room.Model.Room;
import com.room.Model.RoomType;
import com.room.Model.RoomsAvailable;
import com.room.Model.RoomsBooked;
import com.room.Model.Show;
import com.room.Repository.RoomRepository;
import com.room.Repository.SequenceRepository;
import com.room.Service.Util.CheckRoomType;

@Service
public class RoomService {
	//************** Default ****************//
	//sequence Id
	@Value("${sequence.key}")
	private String counterKey;
	
	//prefix for roomId
	@Value("${room.prefix}")
	private String roomPrefix; 
	
	@Value("${room.status.default}")
	private String defaultStatus;
	
	@Value("${room.status.available}")
	private String availableStatus;
	
	@Value("${room.status.booked}")
	private String bookedStatus;
	
	@Value("${room.status.available.nights}")
	private int defaultNights;
	
	private int earnings = 0;

	@Autowired
	SequenceRepository sequenceRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Autowired
	Room bRoom;
	
	@Autowired
	RoomTypeService roomTypeService;
	
	@Autowired
	CheckRoomType cRoomType;
	
	private List<Optional<Room>> allOptionalObjects;

	// add new room
	public Show addNewRoom(Room room) 
	{
		cRoomType.exists(room.getRoomType());
		long sId = sequenceRepository.getNextSequenceId(counterKey);
		String cId = roomPrefix + sId;
		room.setId(cId); 
		room.setBookingDetails(null);
		roomRepository.save(room);
		return new Show("Success! Room  with Id - "+room.getId(),"Added");
	}
	
	// add multiple rooms
	public Show addMultipleRooms(int count, String type) {
		
		int countAll = count;
		
		cRoomType.exists(type);
		while(count>0) {
			long sId = sequenceRepository.getNextSequenceId(counterKey);
			String cId = roomPrefix + sId;
			bRoom.setId(cId);
			bRoom.setRoomType(type);
			bRoom.setStatus(defaultStatus);
			bRoom.setBookingDetails(null);
			roomRepository.save(bRoom);
			count--;
		}
		return new Show("Success! "+countAll," Rooms of Type - "+type+" Added");
	}
	
	//get Room by Id
	public Room getRoomById(String id) 
	{
		try 
		{
			return roomRepository.findById(id).get();
		}
		catch(Exception e) 
		{
			return null;
		}	
	}
	
	// get Room By type
	public List<Room> getRoomsByType(String type)
	{
		allOptionalObjects = roomRepository.findByRoomType(type);
		List<Room> allRoomsByType = allOptionalObjects.stream().map(i->i.get()).collect(Collectors.toList());
		return allRoomsByType;
	}
	
	// get room by status - available/booked
	public List<Room> getRoomsByStatus(String status)
	{
		allOptionalObjects = roomRepository.findByStatus(status);
		List<Room> allRoomsByStatus = allOptionalObjects.stream().map(i->i.get()).collect(Collectors.toList());
		return allRoomsByStatus;
	}
	
	// get all available rooms - count and include them with RoomsAvailable model
	public RoomsAvailable getAllAvailableRooms() 
	{
		List<Room> allAvailableRooms  = getRoomsByStatus(availableStatus);
		return new RoomsAvailable(allAvailableRooms.size(),allAvailableRooms);
	}
	
	// get all booked rooms - count and include them with RoomsBooked model
	public RoomsBooked getAllBookedRooms() 
	{
		List<Room> allBookedRooms = getRoomsByStatus(bookedStatus);
		return new RoomsBooked(allBookedRooms.size(),allBookedRooms);
	}
	
	// Updating status- details
	public Show updateRoomDetails(Room room) throws RoomStatusException
	{
		if((!room.getStatus().equals(availableStatus)) && (!room.getStatus().equals(bookedStatus)))
		{
			throw new RoomStatusException("The entered status "+room.getStatus()+" is  neither "+availableStatus +" nor "+bookedStatus);
		}
		if(room.getStatus().toString().toUpperCase() == availableStatus) 
		{
			room.setBookingDetails(null);
		}
		roomRepository.save(room);
		return  new Show("Success!, Details of Room With Id - "+room.getId(), " Updated");
	}
	
	//Delete room with particular id
	public Show deleteRoomById(String id) {
		Room room = getRoomById(id);
		if(room==null) {
			return new Show("Failed! to delete room with id "+id,"Room doesn't exist");
		}
		roomRepository.deleteById(id);
		return new Show("Success!, Room With Id - "+room.getId() ," Deleted");
	}
	
	//Generate report
	public Report generateEarningsReport(){

		Report report = new Report();
		List<BookedRoomsDetails> bookedRoomsDetailsList = new ArrayList<>();
		List<Room> availableRooms = getRoomsByStatus(availableStatus);
		List<Room> bookedRooms = getRoomsByStatus(bookedStatus);
		
		//count all rooms
		int totalNoOfRooms = availableRooms.size() + bookedRooms.size();
		
		
		RoomType allRoomTypes = roomTypeService.getAllTypes();
		report.setTotalRooms(totalNoOfRooms);
		bookedRooms.stream().forEach(room->{
			BookedRoomsDetails details = new BookedRoomsDetails();
			details.setId(room.getId());
			details.setStatus(room.getStatus());
			details.setDateOfBooking(room.getBookingDetails().getDateOfBooking());
			details.setNights(room.getBookingDetails().getNoOfNights());
			details.setAmount(room.getBookingDetails().getNoOfNights() * (allRoomTypes.getRoomType().get(room.getRoomType())));
			bookedRoomsDetailsList.add(details);
			});
		
		report.setAvailableRooms(availableRooms.size());
		report.setBookedRooms(bookedRooms.size());
		report.setBookedRoomsDetails(bookedRoomsDetailsList);
		bookedRoomsDetailsList.stream().forEach(i->{
			earnings+=i.getAmount();
		});
		report.setTotalEarnings(earnings);
		return report;
	}
}
