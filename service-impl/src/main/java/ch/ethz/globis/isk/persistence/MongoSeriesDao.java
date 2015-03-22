package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoSeries;
import ch.ethz.globis.isk.domain.Series;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MongoSeriesDao extends MongoDao<String, Series> implements SeriesDao {

    @Override
    public Series createEntity() {
        return new MongoSeries();
    }

    @Override
    public Series findOneByName(String name) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("name", new Filter(Operator.EQUAL, name));
        return findOneByFilter(filterMap);
    }

	@Override
	public Class getStoredClass() {
		return MongoSeries.class;
	}
}