package com.ccentre.service;

import java.util.List;
import com.ccentre.entity.*;

public interface UserService {
    CustomUser getUserByLogin(String login);
    boolean existsByLogin(String login);
    void addUser(CustomUser customUser);
    void updateUser(CustomUser customUser);
    List<CustomUser> listUser();
}
