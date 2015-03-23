package ch.ethz.globis.isk.domain;


import ch.ethz.globis.isk.domain.Publication;
import ch.ethz.globis.isk.domain.Series;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoSeries implements Series {
	@Id
    private String id;
    private String name;
    
    @DBRef(lazy = true)
    private Set<Publication> publications;


    public MongoSeries() {
        publications = new HashSet<>();
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

    @Override
    public Set<Publication> getPublications() {
        return publications;
    }

    @Override
    public void setPublications(Set<Publication> publications) {
//        this.publications.clear();
//        this.publications.addAll(publications);
    	this.publications = publications;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Series{");
        sb.append(", id='").append(getId()).append('\'');
        sb.append(", name='").append(getName()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    
}