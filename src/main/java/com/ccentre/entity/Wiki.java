package com.ccentre.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Wiki")
public class Wiki {
    @Id
    @GeneratedValue
    private long wikiId;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "customuser_id")
    private CustomUser customUser;
    @Type(type = "text")
    private String url;
    private Date date;
    /*private Set<UserDocument> userDocuments = new HashSet<UserDocument>(
            0);*/


    public Wiki() {
    }

    public Wiki(Group group, String name, String description, String url, Date date) {
        this.group = group;
        this.name = name;
        this.description = description;
        //this.customUser = customUser;
        this.url = url;
        this.date = date;
    }

    public long getWikiId() {
        return wikiId;
    }

    public void setWikiId(long wikiId) {
        this.wikiId = wikiId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "wiki")
    public Set<UserDocument> getUserDocuments() {
        return this.userDocuments;
    }

    public void setUserDocuments(Set<UserDocument> userDocuments) {
        this.userDocuments = userDocuments;
    }*/
}
