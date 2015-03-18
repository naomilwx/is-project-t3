package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoSchool;
import ch.ethz.globis.isk.domain.School;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MongoSchoolDao extends MongoDao<String, School> implements SchoolDao {
    @Override
    public School createEntity() {
        return new MongoSchool();
    }

    @Override
    public School findOneByName(String name) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("name", new Filter(Operator.EQUAL, name));
        return findOneByFilter(filterMap);
    }

	@Override
	public Class<School> getStoredClass() {
		return School.class;
	}
}