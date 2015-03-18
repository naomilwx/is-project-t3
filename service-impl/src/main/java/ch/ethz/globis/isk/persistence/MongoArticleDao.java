package ch.ethz.globis.isk.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import ch.ethz.globis.isk.domain.Article;
import ch.ethz.globis.isk.domain.MongoArticle;
import ch.ethz.globis.isk.util.Filter;
import ch.ethz.globis.isk.util.Operator;
import ch.ethz.globis.isk.util.Order;
import ch.ethz.globis.isk.util.OrderFilter;

@Repository
public class MongoArticleDao extends MongoDao<String, Article> implements ArticleDao {
	public Class<Article> getStoredClass() {
        return Article.class;
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

    @Override
    public List<Article> findByJournalEditionOrderedByYear(String journalEditionId) {
    	Map<String,Filter> filterMap = new HashMap<String,Filter>();
    	filterMap.put("journalEdition", new Filter(Operator.EQUAL, journalEditionId));
    	List<OrderFilter> orderFilter = new ArrayList<OrderFilter>(); 
    	orderFilter.add(new OrderFilter("year", Order.ASC));
    	List<Article> list = new ArrayList<Article>();
    	for (Article article : findAllByFilter(filterMap,orderFilter)){
    		list.add(article);
    	}
        return list;
    }
}