package ch.ethz.globis.isk.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.MongoInProceedings;
import ch.ethz.globis.isk.domain.InProceedings;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

@Repository
public class MongoInProceedingsDao extends MongoDao<String, InProceedings> implements InProceedingsDao {

    @Override
    public InProceedings findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    @Override
    public List<InProceedings> findByProceedingsIdOrderByYear(String proceedingsId) {
    	//TODO: fix this
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("proceedings", new Filter(Operator.EQUAL,proceedingsId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<InProceedings> list = new ArrayList<InProceedings>();
    	for (InProceedings proceeding : findAllByFilter(filterMap,orderFilter)){
    		list.add(proceeding);
    	}
        return list;
    }

    @Override
    public InProceedings createEntity() {
        return new MongoInProceedings();
    }

	@Override
	public Class<InProceedings> getStoredClass() {
		return InProceedings.class;
	}

}