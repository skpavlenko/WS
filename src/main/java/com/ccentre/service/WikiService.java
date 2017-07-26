package com.ccentre.service;

import java.util.List;
import com.ccentre.entity.*;

public interface WikiService {
    void add(Wiki wiki);
    void delete(Wiki wiki);
    void delete(long[] ids);
    List<Wiki> list();
    List<Wiki> list(Group group);
    List<Wiki> list(String pattern);
    void addGroup(Group group);
    void deleteGroup(Group group);
    List<Group> listGroups();
    Group findGroup(long id);
    Wiki getWikiById(long id);
}
