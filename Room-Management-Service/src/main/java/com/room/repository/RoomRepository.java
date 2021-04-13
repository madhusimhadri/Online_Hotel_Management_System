package com.room.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.room.model.Room;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
	List<Optional<Room>> findByRoomType(String type);
	List<Optional<Room>> findByStatus(String status);
}
