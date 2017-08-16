package com.ccentre.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String name;

    @Type(type = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "customuser_id")
    private CustomUser customUser;

    @Type(type = "text")
    private String url;
    private Date date;

    public Note() {
    }

    public Note(Group group, String name, String description, CustomUser customUser, String url, Date date) {
        this.group = group;
        this.name = name;
        this.description = description;
        this.customUser = customUser;
        this.url = url;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
