package com.practice.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LibraryController {

    @GetMapping
    public ModelAndView whoAreYou() {
        return new ModelAndView("who-are-you");
    }

    @GetMapping("/keeper")
    public ModelAndView keeper() {
        return new ModelAndView("keeper-home");
    }


    @GetMapping("/reader")
    public ModelAndView reader() {
        return new ModelAndView("reader-home");
    }
}