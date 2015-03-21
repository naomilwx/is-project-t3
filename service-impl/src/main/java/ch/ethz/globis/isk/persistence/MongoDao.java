package ch.ethz.globis.isk.persistence;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		return db.findOne(query, getStoredClass());
	}

	@Override
	public T findOneByFilter(Map<String, Filter> filterMap) {
		Query query = new Query();
		for(Map.Entry<String, Filter> filter : filterMap.entrySet()) {
			String filterKey = filter.getKey();
			Filter filterVal = filter.getValue();
			query.addCriteria(Criteria.where(filterKey).is(filterVal));
		}
		return db.findOne(query, getStoredClass());
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap) {
		Query query = new Query();
		for(Map.Entry<String, Filter> filter : filterMap.entrySet()) {
			String filterKey = filter.getKey();
			Filter filterVal = filter.getValue();
			query.addCriteria(Criteria.where(filterKey).is(filterVal));
		}
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
		Query query = new Query();
		int counter = 0;
		for(Map.Entry<String, Filter> filter : filterMap.entrySet()) {
			if(counter++ < start) {
				continue;
			}
			if(counter > size) {
				break;
			}
			String filterKey = filter.getKey();
			Filter filterVal = filter.getValue();
			query.addCriteria(Criteria.where(filterKey).is(filterVal));
		}
		return db.find(query, getStoredClass());
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap,
			List<OrderFilter> orderList) {
		//TODO:
		return null;
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap,
			List<OrderFilter> orderList, int start, int size) {
		//TODO:
		return null;
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
	
}
