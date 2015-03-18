package ch.ethz.globis.isk.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.ethz.globis.isk.domain.Conference;
import ch.ethz.globis.isk.domain.ConferenceEdition;
import ch.ethz.globis.isk.domain.Proceedings;

@Document
public class MongoConferenceEdition implements ConferenceEdition {
	@Id
    private String id;
    private Integer year;
    
    @DBRef
    private Conference conference;
    @DBRef
    private Proceedings proceedings;


    public MongoConferenceEdition() {
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }

    public Proceedings getProceedings() {
        return proceedings;
    }

    public void setProceedings(Proceedings proceedings) {
        this.proceedings = proceedings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

   

    @Override
    public String toString() {
        return "ConferenceEdition{" +
                "id='" + getId() + '\'' +
                ", year=" + getYear() +
                '}';
    }
}