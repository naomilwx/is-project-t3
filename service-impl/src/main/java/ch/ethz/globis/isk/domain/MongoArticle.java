package ch.ethz.globis.isk.domain;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection="publication")
public class MongoArticle extends MongoPublication implements Article {

    private String cdrom;
    private String pages;
    
    @DBRef(lazy = true)
    private JournalEdition journalEdition;

    public MongoArticle() {};

    @Override
    public String getCdrom() {
        return cdrom;
    }

    public void setCdrom(String cdrom) {
        this.cdrom = cdrom;
    }

    public JournalEdition getJournalEdition() {
        return journalEdition;
    }

    public void setJournalEdition(JournalEdition journalEdition) {
        this.journalEdition = journalEdition;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}