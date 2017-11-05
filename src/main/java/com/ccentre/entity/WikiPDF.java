package com.ccentre.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "wikipdf")
public class WikiPDF {
    @Id
    @GeneratedValue
    private long id;

    @Type(type = "blob")
    private Blob pdf;

    public WikiPDF() {
    }

    public WikiPDF(Blob pdf) {
        this.pdf = pdf;
    }

    public Blob getPdf() {
        return pdf;
    }

    public void setPdf(Blob pdf) {
        this.pdf = pdf;
    }
}
