package ch.ethz.globis.isk.domain;


import ch.ethz.globis.isk.domain.Book;
import ch.ethz.globis.isk.domain.InCollection;
import ch.ethz.globis.isk.domain.Publisher;
import ch.ethz.globis.isk.domain.Series;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoBook extends MongoPublication implements Book {

    private String cdrom;
    private Integer month;
    private String volume;
    private String isbn;
    private Series series;
    
    @DBRef(lazy = true)
    private Publisher publisher;
    
    @DBRef(lazy = true)
    private Set<InCollection> publications;
    
    public MongoBook() {
        publications = new HashSet<>();
    }

    @Override
    public String getCdrom() {
        return cdrom;
    }

    @Override
    public void setCdrom(String cdrom) {
        this.cdrom = cdrom;
    }

    @Override
    public String getIsbn() {
        return isbn;
    }

    @Override
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public Integer getMonth() {
        return month;
    }

    @Override
    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public Set<InCollection> getPublications() {
        return publications;
    }

    @Override
    public void setPublications(Set<InCollection> publications) {
//        this.publications.clear();
//        this.publications.addAll(publications);
    	this.publications = publications;
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public Series getSeries() {
        return series;
    }

    @Override
    public void setSeries(Series series) {
        this.series = series;
    }

    @Override
    public String getVolume() {
        return volume;
    }

    @Override
    public void setVolume(String volume) {
        this.volume = volume;
    }
}