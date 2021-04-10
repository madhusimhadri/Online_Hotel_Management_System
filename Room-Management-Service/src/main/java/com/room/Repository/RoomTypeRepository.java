package com.room.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.room.Model.RoomType;

@Repository
public interface RoomTypeRepository extends MongoRepository<RoomType, String> {





}
