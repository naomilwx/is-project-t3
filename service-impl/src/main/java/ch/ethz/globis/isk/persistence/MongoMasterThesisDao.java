package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoMasterThesis;
import ch.ethz.globis.isk.domain.MasterThesis;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MongoMasterThesisDao extends MongoDao<String, MasterThesis> implements MasterThesisDao {


    @Override
    public MasterThesis findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    @Override
    public MasterThesis createEntity() {
        return new MongoMasterThesis();
    }

	@Override
	public Class<MasterThesis> getStoredClass() {
		return MasterThesis.class;
	}
}