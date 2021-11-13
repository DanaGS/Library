package com.practice.library.controllers;

import com.practice.library.entities.Publisher;
import com.practice.library.services.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/reader/publishers/all")
    public ModelAndView listPublishersReader(){
        ModelAndView mav = new ModelAndView("publishers-list-reader");

        List<Publisher> publishers = publisherService.findPublishers();
        mav.addObject("publishers", publishers);

        return mav;
    }

    @GetMapping("/reader/publishers/{namePublisher}/{idPublisher}")
    public ModelAndView seePublisherReader(@PathVariable String namePublisher, @PathVariable Integer idPublisher) {
        ModelAndView mav = new ModelAndView("show-publisher");
        mav.addObject("publisher", publisherService.showPublisher(idPublisher));
        return mav;
    }

    @GetMapping("/keeper/publishers/all")
    public ModelAndView listPublishersKeeper(){
        ModelAndView mav = new ModelAndView("publishers-list-keeper");

        List<Publisher> publishers = publisherService.findPublishers();
        mav.addObject("publishers", publishers);

        return mav;
    }

    @GetMapping("/keeper/publishers/edit/{idPublisher}")
    public ModelAndView editPublisher(@PathVariable Integer idPublisher) {
        ModelAndView mav = new ModelAndView("edit-publisher");
        Publisher publisher = publisherService.showPublisher(idPublisher);
        mav.addObject("publisher", publisher);
        return mav;
    }

    @PostMapping("/keeper/publishers/done")
    public RedirectView editPublisher(@RequestParam Integer idPublisher, @RequestParam String namePublisher,
                                      @RequestParam("aboutPublisher.idDescription") Integer idDescription,
                                      @RequestParam("aboutPublisher.description") String aboutPublisher) {
        publisherService.editPublisher(idPublisher, namePublisher, idDescription, aboutPublisher);
        return new RedirectView("/keeper/publishers/all");
    }

    @PostMapping("/keeper/publishers/delete/{idPublisher}")
    public RedirectView deletePublisher(@PathVariable Integer idPublisher, @RequestParam Integer idDescription) {
        publisherService.deactivatePublisher(idPublisher, idDescription);
        return new RedirectView("/keeper/publishers/all");
    }

    @GetMapping("/keeper/publishers/add")
    public ModelAndView createPublisher(){
        ModelAndView mav = new ModelAndView("add-publisher");
        Publisher publisher = new Publisher();
        mav.addObject("publisher", publisher);
        return mav;
    }

    @PostMapping("/keeper/publishers/save")
    public RedirectView addPublisher(@RequestParam String namePublisher, @RequestParam String aboutPublisher) {
        publisherService.addPublisher(namePublisher, aboutPublisher);
        return new RedirectView("/keeper/publishers/all");
    }
}