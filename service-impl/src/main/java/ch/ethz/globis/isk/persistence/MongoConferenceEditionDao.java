package ch.ethz.globis.isk.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.ConferenceEdition;
import ch.ethz.globis.isk.domain.MongoConferenceEdition;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

@Repository
public class MongoConferenceEditionDao extends MongoDao<String, ConferenceEdition> implements ConferenceEditionDao {


    @Override
    public ConferenceEdition createEntity() {
        return new MongoConferenceEdition();
    }

    @Override
    public List<ConferenceEdition> findByConferenceOrderedByYear(String conferenceId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("conference", new Filter(Operator.EQUAL,conferenceId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<ConferenceEdition> list = new ArrayList<ConferenceEdition>();
    	for (ConferenceEdition conference : findAllByFilter(filterMap,orderFilter)){
    		list.add(conference);
    	}
        return list;
    }

	@Override
	public Class<ConferenceEdition> getStoredClass() {
		return ConferenceEdition.class;
	}
}