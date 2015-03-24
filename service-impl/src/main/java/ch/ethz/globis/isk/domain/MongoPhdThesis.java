package ch.ethz.globis.isk.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.ethz.globis.isk.domain.PhdThesis;
import ch.ethz.globis.isk.domain.Publisher;
import ch.ethz.globis.isk.domain.School;

@Document(collection="publication")
public class MongoPhdThesis extends MongoPublication implements PhdThesis {

    private Integer month;
    private String note;
    private Integer number;
    private String isbn;

    @DBRef(lazy = true)
    private Publisher publisher;
    @DBRef(lazy = true)
    private School school;

    public MongoPhdThesis() { }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if (this.getIsbn() != null) {
            if (this.getNote() == null) {
                this.setNote("");
            }
            this.setNote(this.getNote() + "\nISBN updated, old value was " + this.getIsbn());
        }
        this.isbn = isbn;
        this.isbn = isbn;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}