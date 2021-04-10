package com.employee.Repository;

import com.employee.Exception.SequenceNotFound;

public interface SequenceRepository {
	
	long getNextSequenceId(String key) throws SequenceNotFound;
}
