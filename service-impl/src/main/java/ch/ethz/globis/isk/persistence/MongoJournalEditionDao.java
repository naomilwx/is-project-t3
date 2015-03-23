package ch.ethz.globis.isk.persistence;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.JournalEdition;
import ch.ethz.globis.isk.domain.MongoJournalEdition;

@Repository
public class MongoJournalEditionDao extends MongoDao<String, JournalEdition> implements JournalEditionDao {


    @Override
    public JournalEdition createEntity() {
        return new MongoJournalEdition();
    }

    @Override
    public List<JournalEdition> findByJournalIdOrdered(String journalId) {
    	Query query = findBySubdocumentIdOrderedByYear("journal", journalId);    	
        return mongoOperations.find(query, getStoredClass());
    }

	@Override
	public Class getStoredClass() {
		return MongoJournalEdition.class;
	}
}
