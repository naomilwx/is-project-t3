package ch.ethz.globis.isk.domain;

import ch.ethz.globis.isk.domain.Person;
import ch.ethz.globis.isk.domain.Publication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="publication")
public class MongoPublication implements Publication {
	@Id
    private String id;
    private String title;
    private String electronicEdition;
    private int year;

    @DBRef(lazy = true)
    private Set<Person> authors;
    @DBRef(lazy = true)
    private Set<Person> editors;
    
    public MongoPublication() {
        authors = new HashSet<>();
        editors = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String key) {
        this.id = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Person> getAuthors() {
        return authors;
    }

    @Override
    public void setAuthors(Set<Person> authors) {
    	this.authors = authors;
    }

    public Set<Person> getEditors() {
        return editors;
    }

    @Override
    public void setEditors(Set<Person> editors) {
    	this.editors = editors;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getElectronicEdition() {
        return electronicEdition;
    }

    public void setElectronicEdition(String electronicEdition) {
        this.electronicEdition = electronicEdition;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Publication{");
        sb.append("id='").append(getId()).append('\'');
        sb.append(", title='").append(getTitle()).append('\'');
        sb.append(", year=").append(getYear());
        sb.append('}');
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publication)) return false;

        Publication that = (Publication) o;

        if (!getId().equals(that.getId())) return false;
        if (getTitle() != null ? !getTitle().equals(that.getTitle()) : that.getTitle() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }
}