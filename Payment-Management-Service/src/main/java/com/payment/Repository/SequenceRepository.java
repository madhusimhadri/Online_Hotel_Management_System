package com.payment.Repository;

import com.payment.exception.SequenceNotFound;

public interface SequenceRepository {
	
	long getNextSequenceId(String key) throws SequenceNotFound;
}
