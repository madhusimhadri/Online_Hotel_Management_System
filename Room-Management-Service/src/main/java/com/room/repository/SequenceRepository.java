package com.room.repository;

import com.room.exception.SequenceNotFound;

public interface SequenceRepository {
	long getNextSequenceId(String key) throws SequenceNotFound;
}
