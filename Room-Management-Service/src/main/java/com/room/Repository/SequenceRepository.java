package com.room.Repository;

import com.room.Exception.SequenceNotFound;

public interface SequenceRepository {
	long getNextSequenceId(String key) throws SequenceNotFound;
}
