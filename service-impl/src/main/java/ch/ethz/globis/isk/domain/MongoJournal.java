package ch.ethz.globis.isk.domain;

import ch.ethz.globis.isk.domain.Journal;
import ch.ethz.globis.isk.domain.JournalEdition;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class MongoJournal implements Journal {
	@Id
    private String id;
    private String name;
    
    @DBRef(lazy = true)
    private Set<JournalEdition> editions;

    public MongoJournal() {
        editions = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<JournalEdition> getEditions() {
        return editions;
    }

    public void addEdition(JournalEdition edition) {
        editions.add(edition);
    }

    public void setEditions(Set<JournalEdition> editions) {
//        this.editions.clear();
//        this.editions.addAll(editions);
    	this.editions = editions;
    }

}