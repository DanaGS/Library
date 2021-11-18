package com.practice.library.controllers;

import com.practice.library.entities.LibraryUser;
import com.practice.library.exceptions.LibraryException;
import com.practice.library.services.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Map;

@Controller
public class LibraryUserController {

    @Autowired
    private LibraryUserService libraryUserService;

    @GetMapping("/register")
    private ModelAndView register(HttpServletRequest request, Principal principal) {
        ModelAndView mav = new ModelAndView("register");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("registrationSuccessMessage", flashMap.get("registrationSuccessMessage"));
            mav.addObject("registrationErrorMessage", flashMap.get("registrationErrorMessage"));
            mav.addObject("username", flashMap.get("username"));
            mav.addObject("userEmailAddress", flashMap.get("userEmailAddress"));
            mav.addObject("password", flashMap.get("password"));
        } else {
            mav.addObject("user", new LibraryUser());
        }

        if (principal != null) {
            mav.setViewName("redirect:/");
        }

        return mav;
    }

    @PostMapping("/register/check")
    public RedirectView addUser(@RequestParam String username, @RequestParam String userEmailAddress, @RequestParam String password, RedirectAttributes attributes) {
        try {
            libraryUserService.createUser(username, userEmailAddress, password);
            attributes.addFlashAttribute("registrationSuccessMessage", "Registration complete! Welcome to the Literary Club!");

        } catch (LibraryException e) {
            attributes.addFlashAttribute("username", username);
            attributes.addFlashAttribute("userEmailAddress", userEmailAddress);
            attributes.addFlashAttribute("password", password);
            attributes.addFlashAttribute("registrationErrorMessage", e.getMsg());
            return new RedirectView("/register");
        }

        return new RedirectView("/login");
    }
}
