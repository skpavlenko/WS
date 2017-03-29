package com.ccentre.service;

import java.util.List;
import com.ccentre.entity.*;

public interface GroupService {
    void add(Group group);

    void delete(Group group);

    Group findOne(long id);

    List<Group> list();
}
