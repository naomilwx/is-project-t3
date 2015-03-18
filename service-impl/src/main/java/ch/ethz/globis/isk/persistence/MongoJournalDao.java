package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoJournal;
import ch.ethz.globis.isk.domain.Journal;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MongoJournalDao extends MongoDao<String, Journal> implements JournalDao {

    @Override
    public Journal findOneByName(String name) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("name", new Filter(Operator.EQUAL, name));
        return findOneByFilter(filterMap);
    }

    @Override
    public Journal createEntity() {
        return new MongoJournal();
    }

	@Override
	public Class<Journal> getStoredClass() {
		return Journal.class;
	}
}