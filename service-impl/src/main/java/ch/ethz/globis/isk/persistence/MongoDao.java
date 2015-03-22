package ch.ethz.globis.isk.persistence;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.DomainObject;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

@Repository
public abstract class MongoDao<K extends Serializable, T extends DomainObject> implements Dao<K, T>{
	@Autowired
	@Qualifier("db")
	protected MongoOperations db;
	
	public abstract Class<T> getStoredClass();

	@Override
	public <S extends T> S insert(S entity) {
		db.insert(entity);
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> insert(Iterable<S> entities) {
		Iterator<S> i = entities.iterator();
		while (i.hasNext()){
			S entity = i.next();
			db.insert(entity);
		}
		return entities;
	}

	@Override
	public T findOne(K id) {
		// I am unsure about this implementation
//		Query query = new Query();
//		query.addCriteria(Criteria.where("_id").is(id));
//		return db.findOne(query, getStoredClass());
		return db.findById(id, getStoredClass());
	}

	@Override
	public T findOneByFilter(Map<String, Filter> filterMap) {
		Query query = constructQueryForFilter(filterMap);
		return db.findOne(query, getStoredClass());
	}
	
	private Query constructQueryForFilter(Map<String, Filter> filterMap){
		Query query = new Query();
		for(Map.Entry<String, Filter> filterItem : filterMap.entrySet()) {
			String filterKey = filterItem.getKey();
			Filter filter = filterItem.getValue();
			Object filterVal =  filter.getValue();
			if(Operator.EQUAL.equals(filter.getOperator())){
				query.addCriteria(Criteria.where(filterKey).is(filterVal));
			}else{
				query.addCriteria(Criteria.where(filterKey).regex((String) filterVal));
			}
			
		}
		return query;
	}
	
	private Query sortQuery(Query query, List<OrderFilter> orderList){
		Sort sort = null;
		for(OrderFilter orderFilter: orderList){
			if(sort != null){
				sort.and(createSort(orderFilter));
			}else{
				sort = createSort(orderFilter);
			}
		}
		if(sort != null){
			query.with(sort);
		}
		return query;
	}
	
	private Sort createSort(OrderFilter orderFilter){
		if(Order.ASC.equals(orderFilter.getOrder())){
			return new Sort(Sort.Direction.ASC, orderFilter.getField());
		}else{
			return new Sort(Sort.Direction.DESC, orderFilter.getField());
		}
	}
	
	private Query limitQuery(Query query, int start, int size){
		return query.skip(start).limit(size);
	}
	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap) {
		Query query = constructQueryForFilter(filterMap);
		return db.find(query, getStoredClass());
	}

	@Override
	public long countAllByFilter(Map<String, Filter> filterMap) {
		long counter = 0;
		Iterator<T> filter = findAllByFilter(filterMap).iterator();
		while(filter.hasNext()) {
			filter.next();
			counter++;
		}
		return counter;
	}
	
	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap,
			int start, int size) {
		Query query = constructQueryForFilter(filterMap).skip(start).limit(size);
		query = limitQuery(query, start, size);
		return db.find(query, getStoredClass());
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap,
			List<OrderFilter> orderList) {
		Query query = constructQueryForFilter(filterMap);
		query = sortQuery(query, orderList);
		return db.find(query, getStoredClass());
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap,
			List<OrderFilter> orderList, int start, int size) {
		Query query = constructQueryForFilter(filterMap);
		query = sortQuery(query, orderList);
		query = limitQuery(query, start, size);
		return db.find(query, getStoredClass());
	}

	@Override
	public Iterable<T> findAll() {
		Query query = new Query();
		return db.find(query, getStoredClass());
	}

	@Override
	public long count() {
		long counter = 0;
		Iterator<T> filter = findAll().iterator();
		while(filter.hasNext()) {
			filter.next();
			counter++;
		}
		return counter;
	}
	
	protected Query findBySubdocumentIdOrderedByYear(String subdocName, String subdocId){
		Query query = new Query();
		query.addCriteria(Criteria.where(subdocName+".id").is(subdocId));
		OrderFilter filter = new OrderFilter("year", Order.ASC);
		query.with(createSort(filter));
		return query;
	}
	
}
