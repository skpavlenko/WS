package com.ccentre.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Wiki")
public class Wiki {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    private String name;
    private String description;
//    private CustomUser customUser;
    private String url;
//    private String date;

    /*@OneToMany(mappedBy = "userDocuments", cascade = CascadeType.ALL)
    private Set<UserDocument> userDocuments = new HashSet<UserDocument>();*/

    public Wiki() {
    }

    public Wiki(Group group, String name, String description, String url) {
        this.group = group;
        this.name = name;
        this.description = description;
        //this.customUser = customUser;
        this.url = url;
 //       this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public CustomUser getCustomUser() {
        return customUser;
    }

    public void setCustomUser(CustomUser customUser) {
        this.customUser = customUser;
    }*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/

    /*public Set<UserDocument> getUserDocuments() {
        return userDocuments;
    }

    public void setUserDocuments(Set<UserDocument> userDocuments) {
        this.userDocuments = userDocuments;
    }*/
}
