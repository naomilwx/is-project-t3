package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoPerson;
import ch.ethz.globis.isk.domain.Person;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MongoPersonDao extends MongoDao<String, Person> implements PersonDao {

    @Override
    public Person createEntity() {
        return new MongoPerson();
    }

    @Override
    public Person findOneByName(String name) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("name", new Filter(Operator.EQUAL, name));
        return findOneByFilter(filterMap);
    }

	@Override
	public Class getStoredClass() {
		return MongoPerson.class;
	}
}