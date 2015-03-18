package ch.ethz.globis.isk.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.MongoInCollection;
import ch.ethz.globis.isk.domain.InCollection;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

@Repository
public class MongoInCollectionDao extends MongoDao<String, InCollection> implements InCollectionDao {

    @Override
    public InCollection findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    @Override
    public List<InCollection> findByBookIdOrderByYear(String bookId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("book", new Filter(Operator.EQUAL,bookId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<InCollection> list = new ArrayList<InCollection>();
    	for (InCollection inCollection : findAllByFilter(filterMap,orderFilter)){
    		list.add(inCollection);
    	}
        return list;
    }

   
    @Override
    public InCollection createEntity() {
        return new MongoInCollection();
    }

	@Override
	public Class<InCollection> getStoredClass() {
		return InCollection.class;
	}

}