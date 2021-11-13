package com.practice.library.services;

import com.practice.library.entities.Book;
import com.practice.library.entities.Publisher;
import com.practice.library.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepo publisherRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private DescriptionService descriptionService;

    @Transactional
    public void addPublisher(String publisherName, String aboutPublisher) {
        Publisher publisher = new Publisher();
        List<Book> books = new ArrayList<>();

        publisher.setNamePublisher(publisherName);
        publisher.setBooks(books);
        publisher.setDeactivationDate(null);
        publisher.setAboutPublisher(descriptionService.createDescription(aboutPublisher));

        publisherRepository.save(publisher);
    }

    @Transactional
    public void editPublisher(Integer idPublisher, String namePublisher, Integer idDescription, String aboutPublisher) {

        Publisher publisher = findPublisherById(idPublisher);

        descriptionService.editDescription(idDescription, aboutPublisher);
        publisherRepository.editPublisher(idPublisher, namePublisher);
    }

    /*

    NOT UTILIZED YET

    @Transactional
    public void addPublisherBooks(Integer idPublisher, Book book) {
        List<Book> books = publisherRepository.getById(idPublisher).getBooks();
        books.add(book);
        // publisherRepository.editBooks(idPublisher, books);
    }

    @Transactional
    public void removePublisherBooks(Integer idPublisher, Book book) {
        List<Book> books = publisherRepository.getById(idPublisher).getBooks();
        books.remove(book);
        bookService.deactivateBook(book.getIsbn());
        // publisherRepository.editBooks(idPublisher, books);
    }

     */

    @Transactional
    public void deactivatePublisher(Integer idPublisher, Integer idDescription) {
        publisherRepository.deactivatePublisher(idPublisher, LocalDate.now());
        descriptionService.deactivateDescription(idDescription);
    }

    @Transactional(readOnly = true)
    public Publisher findPublisherById(Integer idPublisher)  {
        return publisherRepository.getById(idPublisher);
    }

    @Transactional(readOnly = true)
    public Publisher showPublisher(Integer idPublisher) {
        Publisher publisher = publisherRepository.getById(idPublisher);
        publisher.setAboutPublisher(publisher.getAboutPublisher());
        return publisher;
    }

    @Transactional(readOnly = true)
    public List<Publisher> findPublishers() {
        return publisherRepository.searchPublishers();
    }

    @Transactional(readOnly = true)
    public List<Publisher> findPublishersByName(String name) {
        return publisherRepository.searchPublishersByName(name);
    }
}