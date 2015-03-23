package ch.ethz.globis.isk.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.InProceedings;
import ch.ethz.globis.isk.domain.MongoInProceedings;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

@Repository
public class MongoInProceedingsDao extends MongoDao<String, InProceedings> implements InProceedingsDao {

    @Override
    public InProceedings findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    //TODO: fix this
    @Override
    public List<InProceedings> findByProceedingsIdOrderByYear(String proceedingsId) {
    	Query query = findBySubdocumentIdOrderedByYear("proceedings", proceedingsId);    	
        return mongoOperations.find(query, getStoredClass());
    }

    @Override
    public InProceedings createEntity() {
        return new MongoInProceedings();
    }

	@Override
	public Class getStoredClass() {
		return MongoInProceedings.class;
	}

}