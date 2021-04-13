package com.room.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.room.exception.SequenceNotFound;
import com.room.model.SequenceId;

@Repository
public class SequenceRepositoryImplementation implements SequenceRepository {

	@Autowired
	MongoOperations mongoOperations;
	
	@Value("${collection.counter}")
	private String counterDb;
	
	@Override
	public long getNextSequenceId(String key) throws SequenceNotFound {
	
	    Query query = new Query(Criteria.where("_id").is(key));
	   
	    // increment seqId by 1
	    Update update = new Update();
        update.inc("seq", 1);
 
        FindAndModifyOptions options = new FindAndModifyOptions();
        
        //return new inremented Id
        options.returnNew(true);
        
        //Logic
        SequenceId seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);

        if (seqId == null) {
        	//
        	SequenceId seq = new SequenceId(key,0);
        	mongoOperations.insert(seq, counterDb);
        	seqId = mongoOperations.findAndModify(query, update, options, SequenceId.class);
        	if(seqId == null) {
        		throw new SequenceNotFound("Unable to get or create sequence id for key : " + key);
        	}
        	// seq collection is created with initial value = 0. Increment it by 1 every time
        	return seqId.getSeq();
        }
        // return seqId
		return seqId.getSeq();
	}

}
