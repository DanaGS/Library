package com.practice.library.controllers;

import com.practice.library.entities.Author;
import com.practice.library.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/reader/authors/all")
    public ModelAndView listAuthorsReader(){
        ModelAndView mav = new ModelAndView("authors-list-reader");

        List<Author> authors = authorService.findAuthors();
        mav.addObject("authors", authors);

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

        List<Author> authors = authorService.findAuthors();
        mav.addObject("authors", authors);

        return mav;
    }

    @GetMapping("/keeper/authors/edit/{idAuthor}")
    public ModelAndView editAuthor(@PathVariable Integer idAuthor) {
        ModelAndView mav = new ModelAndView("edit-author");
        Author author = authorService.showAuthor(idAuthor);
        mav.addObject("author", author);
        return mav;
    }

    @PostMapping("/keeper/authors/done")
    public RedirectView editAuthorName(@RequestParam Integer idAuthor, @RequestParam String nameAuthor) {
        authorService.editAuthorName(idAuthor,nameAuthor);
        return new RedirectView("/keeper/authors/all");
    }

    @PostMapping("/keeper/authors/delete/{idAuthor}")
    public RedirectView deleteAuthor(@PathVariable Integer idAuthor) {
        authorService.deactivateAuthor(idAuthor);
        return new RedirectView("/keeper/authors/all");
    }

    @GetMapping("/keeper/authors/add")
    public ModelAndView createAuthor(){
        ModelAndView mav = new ModelAndView("add-author");
        Author author = new Author();
        mav.addObject("author", author);
        return mav;
    }

    @PostMapping("/keeper/authors/save")
    public RedirectView addAuthor(@RequestParam String nameAuthor) {
        authorService.addAuthor(nameAuthor);
        return new RedirectView("/keeper/authors/all");
    }
}