package ch.ethz.globis.isk.persistence;

import ch.ethz.globis.isk.domain.MongoPublication;
import ch.ethz.globis.isk.domain.Publication;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MongoPublicationDao extends MongoDao<String, Publication> implements PublicationDao {

    @Override
    public Publication createEntity() {
        return new MongoPublication();
    }

    @Override
    public Publication findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    @Override
    public List<Publication> findByAuthorIdOrderedByYear(String authorId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("author", new Filter(Operator.EQUAL,authorId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<Publication> list = new ArrayList<Publication>();
    	for (Publication publication : findAllByFilter(filterMap)){
    		list.add(publication);
    	}
        return list;
    }

    @Override
    public List<Publication> findByEditorIdOrderedByYear(String editorId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("editor", new Filter(Operator.EQUAL,editorId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<Publication> list = new ArrayList<Publication>();
    	for (Publication publication : findAllByFilter(filterMap,orderFilter)){
    		list.add(publication);
    	}
        return list;
    }

    @Override
    public List<Publication> findByPublisherOrderedByYear(String publisherId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("publisher", new Filter(Operator.EQUAL,publisherId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<Publication> list = new ArrayList<Publication>();
    	for (Publication publication : findAllByFilter(filterMap,orderFilter)){
    		list.add(publication);
    	}
        return list;
    }

    @Override
    public List<Publication> findBySchoolOrderedByYear(String schoolId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("school", new Filter(Operator.EQUAL,schoolId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<Publication> list = new ArrayList<Publication>();
    	for (Publication publication : findAllByFilter(filterMap,orderFilter)){
    		list.add(publication);
    	}
        return list;
    }

    @Override
    public List<Publication> findBySeriesOrderedByYear(String seriesId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("series", new Filter(Operator.EQUAL,seriesId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<Publication> list = new ArrayList<Publication>();
    	for (Publication publication : findAllByFilter(filterMap,orderFilter)){
    		list.add(publication);
    	}
        return list;
    }

	@Override
	public Class<Publication> getStoredClass() {
		return Publication.class;
	}
}