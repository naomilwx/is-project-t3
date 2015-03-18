package ch.ethz.globis.isk.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.ethz.globis.isk.domain.Book;
import ch.ethz.globis.isk.domain.InCollection;
@Document
public class MongoInCollection extends MongoPublication implements InCollection {

    private String note;
    private String pages;
    
    @DBRef
    private Book parentPublication;

    public MongoInCollection() { }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public Book getParentPublication() {
        return parentPublication;
    }

    public void setParentPublication(Book book) {
        this.parentPublication = book;
    }
}