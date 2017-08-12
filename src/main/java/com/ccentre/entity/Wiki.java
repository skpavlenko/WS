package com.ccentre.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.*;

@Entity
@Table(name = "wiki")
public class Wiki {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String name;

    @ManyToOne
    @JoinColumn(name = "customuser_id")
    private CustomUser customUser;

    @OneToOne
    @JoinColumn(name = "wikitext_id")
    private WikiText wikiText;

    @Type(type = "text")
    private String url;
    private Date date;

    @OneToMany(mappedBy = "wiki", cascade = CascadeType.ALL)
    private List<UserDocument> userDocuments = new ArrayList<UserDocument>();

    @Type(type = "blob")
    private Blob pdf;

    public Wiki() {
    }

    public Wiki(Group group, String name, String description, CustomUser customUser, String url, Date date) {
        this.group = group;
        this.name = name;
        this.customUser = customUser;
        this.url = url;
        this.date = date;
    }

    public Wiki(Group group, String name, String description, CustomUser customUser, String url, Date date, Blob pdf) {
        this.group = group;
        this.name = name;
        this.customUser = customUser;
        this.url = url;
        this.date = date;
        this.pdf = pdf;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<UserDocument> getUserDocuments() {
        return userDocuments;
    }

    public void setUserDocuments(List<UserDocument> userDocuments) {
        this.userDocuments = userDocuments;
    }

    public Blob getPdf() {
        return pdf;
    }

    public void setPdf(Blob pdf) {
        this.pdf = pdf;
    }
}
