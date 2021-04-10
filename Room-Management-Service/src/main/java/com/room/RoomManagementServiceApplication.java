package com.room;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.room.Model.Room;
import com.room.Model.RoomType;

@SpringBootApplication
public class RoomManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoomManagementServiceApplication.class, args);
	}
	
	@Bean
	public RoomType getRoomTypeObject() {
		return new RoomType();
	}
	@Bean
	public Room getRoomObject() {
		return new Room();
	}
}
