package com.practice.library.controllers;

import com.practice.library.entities.Author;
import com.practice.library.exceptions.LibraryException;
import com.practice.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/reader/authors/all")
    public ModelAndView listAuthorsReader(){
        ModelAndView mav = new ModelAndView("authors-list-reader");
        mav.addObject("authors", authorService.findAuthors());
        return mav;
    }

    @GetMapping("/reader/authors/{nameAuthor}/{idAuthor}")
    public ModelAndView seeAuthorReader(@PathVariable String nameAuthor, @PathVariable Integer idAuthor) {
        ModelAndView mav = new ModelAndView("show-author");
        mav.addObject("author", authorService.showAuthor(idAuthor));
        return mav;
    }

    @GetMapping("/keeper/authors/all")
    public ModelAndView listAuthorsKeeper(){
        ModelAndView mav = new ModelAndView("authors-list-keeper");
        mav.addObject("authors", authorService.findAuthors());
        return mav;
    }

    @GetMapping("/keeper/authors/edit/{idAuthor}")
    public ModelAndView editAuthor(@PathVariable Integer idAuthor) {
        ModelAndView mav = new ModelAndView("edit-author");
        mav.addObject("author", authorService.showAuthor(idAuthor));
        return mav;
    }

    @PostMapping("/keeper/authors/done")
    public RedirectView editAuthor(@RequestParam Integer idAuthor, @RequestParam String nameAuthor,
                                       @RequestParam("aboutAuthor.idDescription") Integer idDescription,
                                       @RequestParam("aboutAuthor.description") String aboutAuthor) {

        RedirectView redirectView = new RedirectView("/keeper/authors/all");
        authorService.editAuthor(idAuthor, nameAuthor, idDescription ,aboutAuthor);
        return redirectView;
    }

    @PostMapping("/keeper/authors/delete/{idAuthor}")
    public RedirectView deleteAuthor(@PathVariable Integer idAuthor, @RequestParam Integer idDescription) {
        authorService.deactivateAuthor(idAuthor, idDescription);
        return new RedirectView("/keeper/authors/all");
    }

    @GetMapping("/keeper/authors/add")
    public ModelAndView createAuthor(){
        ModelAndView mav = new ModelAndView("add-author");
        mav.addObject("author", new Author());
        return mav;
    }

    @PostMapping("/keeper/authors/save")
    public RedirectView addAuthor(@RequestParam String nameAuthor, @RequestParam String aboutAuthor) {

        RedirectView redirectView = new RedirectView("/keeper/authors/all");
        authorService.addAuthor(nameAuthor, aboutAuthor);
        return redirectView;
    }
}