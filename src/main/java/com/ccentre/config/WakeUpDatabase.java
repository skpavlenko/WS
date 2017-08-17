package com.ccentre.config;

import com.ccentre.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
@EnableScheduling
public class WakeUpDatabase {

    @Autowired
    private UserService userService;

    @Scheduled(fixedDelay = 21600000)
    public void smartQuery(){
        userService.listUser();
    }
}
