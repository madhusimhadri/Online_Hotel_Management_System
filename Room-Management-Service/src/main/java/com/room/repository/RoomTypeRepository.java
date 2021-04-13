package com.room.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.room.model.RoomType;

@Repository
public interface RoomTypeRepository extends MongoRepository<RoomType, String> {

}
