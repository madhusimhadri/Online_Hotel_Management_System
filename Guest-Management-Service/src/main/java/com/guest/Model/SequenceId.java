package com.guest.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "SequenceStore")

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
