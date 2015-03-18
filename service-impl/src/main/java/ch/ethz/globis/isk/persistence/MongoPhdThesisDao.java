package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoPhdThesis;
import ch.ethz.globis.isk.domain.PhdThesis;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MongoPhdThesisDao extends MongoDao<String, PhdThesis> implements PhdThesisDao {
    @Override
    public PhdThesis findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    @Override
    public PhdThesis createEntity() {
        return new MongoPhdThesis();
    }

	@Override
	public Class<PhdThesis> getStoredClass() {
		return PhdThesis.class;
	}
}