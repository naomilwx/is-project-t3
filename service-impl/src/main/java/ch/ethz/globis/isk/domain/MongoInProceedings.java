package ch.ethz.globis.isk.domain;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import ch.ethz.globis.isk.domain.InProceedings;
import ch.ethz.globis.isk.domain.Proceedings;
@Document
public class MongoInProceedings extends MongoPublication implements InProceedings {

    private String note;
    private String pages;
    
    @DBRef
    private Proceedings proceedings;

    public MongoInProceedings() {}

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

    public Proceedings getProceedings() {
        return proceedings;
    }

    @Override
    public void setProceedings(Proceedings proceedings) {
        this.proceedings = proceedings;
    }
}