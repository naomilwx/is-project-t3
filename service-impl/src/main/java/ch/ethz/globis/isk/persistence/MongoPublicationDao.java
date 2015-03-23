package ch.ethz.globis.isk.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.MongoPublication;
import ch.ethz.globis.isk.domain.Publication;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

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

    //TODO: this does not actually work because there is no document of class MongoPublication,
    //The documents we are interested in are subclasses of MongoPublication
    @Override
    public List<Publication> findByAuthorIdOrderedByYear(String authorId) {
        Query query = findBySubdocumentIdOrderedByYear("author", authorId);  
        return mongoOperations.find(query, getStoredClass());
    }

    @Override
    public List<Publication> findByEditorIdOrderedByYear(String editorId) {
    	Query query = findBySubdocumentIdOrderedByYear("editor", editorId);    	
        return mongoOperations.find(query, getStoredClass());
    }

    @Override
    public List<Publication> findByPublisherOrderedByYear(String publisherId) {
    	Query query = findBySubdocumentIdOrderedByYear("publisher", publisherId);    	
        return mongoOperations.find(query, getStoredClass());
    }

    @Override
    public List<Publication> findBySchoolOrderedByYear(String schoolId) {
    	Query query = findBySubdocumentIdOrderedByYear("school", schoolId);    	
        return mongoOperations.find(query, getStoredClass());
    }

    @Override
    public List<Publication> findBySeriesOrderedByYear(String seriesId) {
    	Query query = findBySubdocumentIdOrderedByYear("series", seriesId);    	
        return mongoOperations.find(query, getStoredClass());
    }

	@Override
	public Class getStoredClass() {
		return MongoPublication.class;
	}
}