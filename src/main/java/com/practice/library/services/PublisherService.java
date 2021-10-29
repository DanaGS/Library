package com.practice.library.services;

import com.practice.library.entities.Book;
import com.practice.library.entities.Publisher;
import com.practice.library.repository.BookRepo;
import com.practice.library.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepo publisherRepository;

    @Autowired
    private BookRepo bookRepository; // ???????????????????

    @Transactional
    public void addPublisher(String publisherName) {
        Publisher publisher = new Publisher();

        publisher.setNamePublisher(publisherName);
        publisher.setBooks(null);
        publisher.setCreationDate(LocalDate.now());
        publisher.setLastModificationDate(LocalDate.now());
        publisher.setDeactivationDate(null);

        publisherRepository.save(publisher);
    }

    @Transactional
    public void editPublisherName(Integer idPublisher, String namePublisher) {
        publisherRepository.editPublisherName(idPublisher, namePublisher, LocalDate.now());
    }

    @Transactional
    public void addPublisherBooks(Integer idPublisher, Book book) { // ?????????????????????
        List<Book> books = publisherRepository.getById(idPublisher).getBooks();
        books.add(book);
        publisherRepository.editBooks(idPublisher, books, LocalDate.now());
    }

    @Transactional
    public void removePublisherBooks(Integer idPublisher, Book book) {
        List<Book> books = publisherRepository.getById(idPublisher).getBooks();
        books.remove(book);
        bookRepository.deactivateBook(book.getIsbn(), LocalDate.now(), LocalDate.now());
        publisherRepository.editBooks(idPublisher, books, LocalDate.now());
    }

    @Transactional
    public void deactivatePublisher(Integer idPublisher) {
        publisherRepository.deactivatePublisher(idPublisher, LocalDate.now(), LocalDate.now());
    }

    @Transactional(readOnly = true)
    public Publisher showPublisher(Integer idPublisher)  {
        return publisherRepository.getById(idPublisher);
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