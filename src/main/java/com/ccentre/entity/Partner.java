package com.ccentre.entity;

import org.hibernate.annotations.Type;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Blob;

@Entity
public class Partner implements Comparable<Partner>{
    @Id
    @GeneratedValue
    private long id;

    private String name;
    @Type(type = "text")
    private String topic;
    private int ord;
    private String url;
    @Type(type = "blob")
    private Blob logo;

    public Partner(String name, String topic, String url, Blob logo) {
        this.name = name;
        this.topic = topic;
        this.url = url;
        this.logo = logo;
    }

    public Partner(String name, String topic, String url) {
        this.name = name;
        this.topic = topic;
        this.url = url;
    }

    public Partner() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Blob getLogo() {
        return logo;
    }

    public void setLogo(Blob logo) {
        this.logo = logo;
    }

    @Override
    public int compareTo(Partner p) {
        return ord > p.ord ? 1 : ord < p.ord ? -1 : 0;
    }
}
