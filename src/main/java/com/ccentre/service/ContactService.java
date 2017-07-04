package com.ccentre.service;

import java.util.List;
import com.ccentre.entity.*;

public interface ContactService {
    void add(Contact contact);
    void delete(Contact contact);
    void delete(long[] ids);
    List<Contact> list();
    List<Contact> list(Group group);
    List<Contact> list(String pattern);
    void addGroup(Group group);
    void deleteGroup(Group group);
    List<Group> listGroups();
    Group findGroup(long id);
    Contact getContactById(long id);
}
