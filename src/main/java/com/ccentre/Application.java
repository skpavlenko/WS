package com.ccentre;


import com.ccentre.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.ccentre.entity.*;
import com.ccentre.entity.enums.*;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<Application> applicationClass = Application.class;

    @Bean
    public CommandLineRunner demo(final UserService userService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                if (userService.listUser().size()==0)
                userService.addUser(new CustomUser("admin", "d033e22ae348aeb5660fc2140aec35850c4da997", UserRole.ADMIN));
                //userService.addUser(new CustomUser("user", "12dea96fec20593566ab75692c9949596833adc9", UserRole.USER));
            }
        };
    }
}