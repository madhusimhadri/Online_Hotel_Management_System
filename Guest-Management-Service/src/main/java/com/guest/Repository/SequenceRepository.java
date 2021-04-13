package com.guest.repository;

import com.guest.exception.SequenceNotFound;

public interface SequenceRepository {
	
	long getNextSequenceId(String key) throws SequenceNotFound;
}
