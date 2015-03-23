package ch.ethz.globis.isk.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.MongoPerson;
import ch.ethz.globis.isk.domain.MongoPublication;
import ch.ethz.globis.isk.domain.MongoPublisher;
import ch.ethz.globis.isk.domain.MongoSchool;
import ch.ethz.globis.isk.domain.MongoSeries;
import ch.ethz.globis.isk.domain.Publication;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

@Repository
public class MongoPublicationDao extends MongoDao<String, Publication> implements PublicationDao {

    @Override
    public Publication createEntity() {
        return new MongoPublication();
    }
    
    private Comparator<Publication> createYearComparator(){
    	Comparator<Publication> comparator = new Comparator<Publication>(){

			@Override
			public int compare(Publication o1, Publication o2) {
				
				// TODO Auto-generated method stub
				return o1.getYear().compareTo(o2.getYear());
			}};
			
		return comparator;
    }

    @Override
    public Publication findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    @Override
    public List<Publication> findByAuthorIdOrderedByYear(String authorId) {
    	MongoPerson author = mongoOperations.findById(authorId, MongoPerson.class);
    	ArrayList<Publication> pubs = new ArrayList<>(author.getAuthoredPublications());
    	Collections.sort(pubs, createYearComparator());
//        Query query = findBySubdocumentIdOrderedByYear("author", authorId);  
//        return mongoOperations.find(query, getStoredClass());
    	return pubs;
    }

    @Override
    public List<Publication> findByEditorIdOrderedByYear(String editorId) {
//    	Query query = findBySubdocumentIdOrderedByYear("editor", editorId);    	
//        return mongoOperations.find(query, getStoredClass());
    	MongoPerson editor = mongoOperations.findById(editorId, MongoPerson.class);
    	ArrayList<Publication> pubs = new ArrayList<>(editor.getEditedPublications());
    	Collections.sort(pubs, createYearComparator());
    	return pubs;
    }

    @Override
    public List<Publication> findByPublisherOrderedByYear(String publisherId) {
//    	Query query = findBySubdocumentIdOrderedByYear("publisher", publisherId);    	
//        return mongoOperations.find(query, getStoredClass());
        MongoPublisher publisher = mongoOperations.findById(publisherId, MongoPublisher.class);
        ArrayList<Publication> pubs = new ArrayList<>(publisher.getPublications());
    	Collections.sort(pubs, createYearComparator());
    	return pubs;
    }

    @Override
    public List<Publication> findBySchoolOrderedByYear(String schoolId) {
//    	Query query = findBySubdocumentIdOrderedByYear("school", schoolId);    	
//        return mongoOperations.find(query, getStoredClass());
    	MongoSchool school = mongoOperations.findById(schoolId, MongoSchool.class);
    	ArrayList<Publication> pubs = new ArrayList<>(school.getPublications());
     	Collections.sort(pubs, createYearComparator());
     	return pubs;
    }

    @Override
    public List<Publication> findBySeriesOrderedByYear(String seriesId) {
//    	Query query = findBySubdocumentIdOrderedByYear("series", seriesId);    	
//        return mongoOperations.find(query, getStoredClass());
    	MongoSeries series = mongoOperations.findById(seriesId, MongoSeries.class);
    	ArrayList<Publication> pubs = new ArrayList<>(series.getPublications());
     	Collections.sort(pubs, createYearComparator());
     	return pubs;
    }

	@Override
	public Class getStoredClass() {
		return MongoPublication.class;
	}
}