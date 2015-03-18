package ch.ethz.globis.isk.domain;

import ch.ethz.globis.isk.domain.Article;
import ch.ethz.globis.isk.domain.Journal;
import ch.ethz.globis.isk.domain.JournalEdition;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class MongoJournalEdition implements JournalEdition {
	@Id
    private String id;
	
    private String number;
    private String volume;
    private Integer year;
    
    @DBRef
    private Journal journal;
    
    @DBRef(lazy = true)
    private Set<Article> publications;

    public MongoJournalEdition() {
        publications = new HashSet<>();
    }

    public void addArticle(Article article) {
        publications.add(article);
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Article> getPublications() {
        return publications;
    }

    public void setPublications(Set<Article> publications) {
        this.publications.clear();
        this.publications.addAll(publications);
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JournalEdition{" +
                "year=" + getYear() +
                ", volume='" +  getYear() + '\'' +
                ", number='" +  getYear() + '\'' +
                ", id='" +  getYear() + '\'' +
                '}';
    }

  
}