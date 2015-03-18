package ch.ethz.globis.isk.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.MongoJournalEdition;
import ch.ethz.globis.isk.domain.JournalEdition;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

@Repository
public class MongoJournalEditionDao extends MongoDao<String, JournalEdition> implements JournalEditionDao {


    @Override
    public JournalEdition createEntity() {
        return new MongoJournalEdition();
    }

    @Override
    public List<JournalEdition> findByJournalIdOrdered(String journalId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("journal", new Filter(Operator.EQUAL,journalId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<JournalEdition> list = new ArrayList<JournalEdition>();
    	for (JournalEdition edition : findAllByFilter(filterMap,orderFilter)){
    		list.add(edition);
    	}
        return list;
    }

	@Override
	public Class<JournalEdition> getStoredClass() {
		return JournalEdition.class;
	}
}
