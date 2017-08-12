package com.ccentre.entity;

import javax.persistence.*;

@Entity
@Table(name = "wikitext")
public class WikiText {
    @Id
    @GeneratedValue
    private long id;
    @Lob
    private String description;

    public WikiText() {
    }

    public WikiText(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
