package ch.ethz.globis.isk.domain;

import ch.ethz.globis.isk.domain.Person;
import ch.ethz.globis.isk.domain.Publication;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document
public class MongoPerson implements Person {
	@Id
    private String id;
    private String name;

    @DBRef(lazy = true)
    public Set<Publication> authoredPublications;
    @DBRef(lazy = true)
    public Set<Publication> editedPublications;


    public MongoPerson() {
        authoredPublications = new HashSet<>();
        editedPublications = new HashSet<>();
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

    public Set<Publication> getAuthoredPublications() {
        return authoredPublications;
    }

    public void setAuthoredPublications(Set<Publication> publications) {
    	this.authoredPublications = publications;
    }

    public Set<Publication> getEditedPublications() {
        return editedPublications;
    }

    public void setEditedPublications(Set<Publication> publications) {
    	this.editedPublications = publications;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (getId() != null ? !getId().equals(person.getId()) : person.getId() != null) return false;
        if (getName() != null ? !getName().equals(person.getName()) : person.getName() != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "MongoPerson{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

}