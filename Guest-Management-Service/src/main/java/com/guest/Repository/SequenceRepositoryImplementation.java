package com.guest.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.guest.Exception.SequenceNotFound;
import com.guest.Model.SequenceId;

@Repository
public class SequenceRepositoryImplementation implements SequenceRepository {

	@Autowired
	MongoOperations mongoOperations;
	
	@Value("${collection.counter}")
	private String counter;
	
	@Override
	public long getNextSequenceId(String key) throws SequenceNotFound {
		
		//Get Sequence Id
		Query query = new Query(Criteria.where("_id").is(key));
		
		//Increment Sequence Id by 1
		Update update = new Update();
		update.inc("seq",1);
		
		//Return Incremented Id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		
		SequenceId seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
		
		if(seqId == null)
		{
			SequenceId seq = new SequenceId(key,0);
			mongoOperations.insert(seq,counter);
			seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
			
			if(seqId == null)
			{
				throw new SequenceNotFound("Unable to Create SequenceId for Key: "+key);
			}
			// Create a seq, add first id as 0, increment it by 1 and return the value
			return seqId.getSeq();
		}
		return seqId.getSeq();		
	}
	
	
}
