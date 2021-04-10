package com.room.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.room.Model.Room;
import com.room.Repository.RoomRepository;
import com.room.Service.RoomService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTest {

	@Autowired
	private RoomService roomService;
	
	@MockBean
	private RoomRepository roomRepository;
	

	@Test
	public void testAddNewRoom()
	{
	Room room = new Room();
	room.setId("r1");
	room.setRoomType("SINGLE");
	room.setStatus("AVAILABLE");
	
	Mockito.when(roomRepository.save(room)).thenReturn(room);
	assertEquals("Success! Room with Id - "+room.getId(),"Success! Room with Id - r1");

	}

	@Test
	public void testgetRoomById()
	{
		Optional<Room> room = Optional.of(new Room());
		room.get().setId("r1");
		room.get().setRoomType("SINGLE");
		room.get().setStatus("AVAILABLE");
		
		Mockito.when(roomRepository.findById("r1").get()).thenReturn(room.get());
		assertThat(roomService.getRoomById("r1")).isEqualTo(room);
	}
	
	/*
	@Test
	public void testgetAllAvailableRooms()
	{
		Optional<Room> room = Optional.of(new Room());
		room.get().setId("r1");
		room.get().setRoomType("SINGLE");
		room.get().setStatus("AVAILABLE");
		
		List<Optional<Room>> roomList = new ArrayList<>();
		roomList.add(room);
		
		Mockito.when(roomRepository.findByStatus("AVAIALABLE")).thenReturn(roomList);
		assertThat(roomService.getAllAvailableRooms()).isEqualTo(roomList);
	}
	
	@Test
	public void testgetRoomsByStatus()
	{
		
	}
	
	@Test
	public void testgetRoomsByType()
	{
		
	}
	
	@Test
	public void testaddMultipleRooms()
	{
		
	}
	
	*/

	@Test
	public void testgetAllBookedRooms()
	{
		Room room1 = new Room();
		room1.setId("r1");
		room1.setRoomType("single");
		room1.setStatus("BOOKED");
		
		Room room2 = new Room();
		room2.setId("r2");
		room2.setRoomType("single");
		room2.setStatus("BOOKED");

		List<Room> roomList = new ArrayList<>();
		roomList.add(room1);
		roomList.add(room2);
	
		Mockito.when(roomRepository.findAll()).thenReturn(roomList);
		assertThat(roomService.getAllBookedRooms()).isEqualTo(roomList);
	
	}
	
	@Test
	public void testDeleteRoomById()
	{
		Optional<Room> room = Optional.of(new Room());
		room.get().setId("r1");
		room.get().setRoomType("single");
		room.get().setStatus("AVAILABLE");
		
		Mockito.when(roomRepository.findById("r1")).thenReturn(room);
		Mockito.when(roomRepository.existsById(room.get().getId())).thenReturn(false);
		assertFalse(roomRepository.existsById(room.get().getId()));
		

	}
		
	@Test
	public void testUpdateRoomDetails()
	{
		Optional<Room> room = Optional.of(new Room());
		room.get().setId("r1");
		room.get().setRoomType("single");
		room.get().setStatus("AVAILABLE");
		
		Mockito.when(roomRepository.save(room.get())).thenReturn(room.get());
		assertThat(roomService.updateRoomDetails(room.get())).isEqualTo("Success!, Details of Room With Id - "+room.get().getId()+ " Updated");
		
	}
	
	
	
	
	
	
}
