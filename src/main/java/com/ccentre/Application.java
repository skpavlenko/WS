package com.ccentre;


import com.ccentre.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ccentre.entity.*;
import com.ccentre.entity.enums.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
@ComponentScan
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        DispatcherServlet dispatcherServlet = (DispatcherServlet)ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
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
            }
        };
    }

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        servletContext.setInitParameter("contextConfigLocation", "<NONE>");
        super.onStartup(servletContext);
    }

}