package ch.ethz.globis.isk.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.Article;
import ch.ethz.globis.isk.domain.MongoArticle;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;

@Repository
public class MongoArticleDao extends MongoDao<String, Article> implements ArticleDao {
	public Class getStoredClass() {
        return MongoArticle.class;
    }

    @Override
    public Article createEntity() {
        return new MongoArticle();
    }

    @Override
    public Article findOneByTitle(String title) {
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("title", new Filter(Operator.EQUAL, title));
        return findOneByFilter(filterMap);
    }

    //TODO: fix this
    @Override
    public List<Article> findByJournalEditionOrderedByYear(String journalEditionId) {
    	Query query = findBySubdocumentIdOrderedByYear("journalEdition", journalEditionId);    	
        return mongoOperations.find(query, getStoredClass());
    }
}