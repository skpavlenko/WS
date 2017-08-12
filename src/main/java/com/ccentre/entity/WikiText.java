package com.ccentre.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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


}
