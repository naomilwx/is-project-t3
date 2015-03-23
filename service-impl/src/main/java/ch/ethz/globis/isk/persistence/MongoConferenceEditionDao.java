package ch.ethz.globis.isk.persistence;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.ConferenceEdition;
import ch.ethz.globis.isk.domain.MongoConferenceEdition;

@Repository
public class MongoConferenceEditionDao extends MongoDao<String, ConferenceEdition> implements ConferenceEditionDao {


    @Override
    public ConferenceEdition createEntity() {
        return new MongoConferenceEdition();
    }

    //TODO: fix this
    @Override
    public List<ConferenceEdition> findByConferenceOrderedByYear(String conferenceId) {
    	Query query = findBySubdocumentIdOrderedByYear("conference", conferenceId);
        return mongoOperations.find(query, getStoredClass());
    }

	@Override
	public Class getStoredClass() {
		return MongoConferenceEdition.class;
	}
}