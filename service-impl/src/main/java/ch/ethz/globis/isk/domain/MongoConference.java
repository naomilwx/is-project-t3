package ch.ethz.globis.isk.domain;

import ch.ethz.globis.isk.domain.Conference;
import ch.ethz.globis.isk.domain.ConferenceEdition;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoConference implements Conference {
	
	@Id
    private String id;
    private String name;
    
    @DBRef(lazy = true)
    private Set<ConferenceEdition> editions;

    public MongoConference() {
        editions = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ConferenceEdition> getEditions() {
        return editions;
    }

    public void setEditions(Set<ConferenceEdition> editions) {
        this.editions.clear();
        this.editions.addAll(editions);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}