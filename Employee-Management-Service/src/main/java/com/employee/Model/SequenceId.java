package com.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// To Store the sequence for the sequence id generated since auto increment is not available in mongoDb unlike MySQl
@Document(collection = "SequenceStore")
public class SequenceId {
	
	@Id
	private String id;
	private long seq;
	
	//Default
	public SequenceId()
	{
		
	}

	public SequenceId(String id, long seq) {
		super();
		this.id = id;
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}
	
	
	
}
