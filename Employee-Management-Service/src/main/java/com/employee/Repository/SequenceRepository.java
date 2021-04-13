package com.employee.repository;

import com.employee.exception.SequenceNotFound;

public interface SequenceRepository {
	
	long getNextSequenceId(String key) throws SequenceNotFound;
}
