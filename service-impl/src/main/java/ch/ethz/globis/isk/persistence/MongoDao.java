package ch.ethz.globis.isk.persistence;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoOperations;

import ch.ethz.globis.isk.domain.DomainObject;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;


public abstract class MongoDao<K extends Serializable, T extends DomainObject> implements Dao<K, T>{
	@Autowired
	@Qualifier("db")
	protected MongoOperations db;
	
	public abstract Class<T> getStoredClass();

	@Override
	public <S extends T> S insert(S entity) {
		//TODO:
		return entity;
	}

	@Override
	public <S extends T> Iterable<S> insert(Iterable<S> entities) {
		Iterator<S> i = entities.iterator();
		while (i.hasNext()){
//			TODO:
		}
		return entities;
	}

	@Override
	public T findOne(K id) {
		//TODO:
		return null;
	}

	@Override
	public T findOneByFilter(Map<String, Filter> filterMap) {
		//TODO:
		return null;
	}

	@Override
	public long countAllByFilter(Map<String, Filter> filterMap) {
//		ObjectSet<T> set = (ObjectSet<T>) findAllByFilter(filterMap);
		//TODO:
		return 0;
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap) {
		//TODO:
		return null;
	}

	@Override
	public Iterable<T> findAllByFilter(Map<String, Filter> filterMap,
			int start, int size) {
		//TODO:
//		ObjectSet<T> set = (ObjectSet<T>) findAllByFilter(filterMap);
//		List<T> subset = set.subList(start, start+size);
		return null;
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
		//TODO:
		return null;
	}

	@Override
	public long count() {
		//TODO:
		return 0;
	}
	
}
