package com.ccentre.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String handle(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String globalHandle(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
