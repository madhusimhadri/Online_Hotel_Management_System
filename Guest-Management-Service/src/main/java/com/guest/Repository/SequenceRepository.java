package com.guest.Repository;

import com.guest.Exception.SequenceNotFound;

public interface SequenceRepository {
	
	long getNextSequenceId(String key) throws SequenceNotFound;
}
