package com.practice.library.services;

import com.practice.library.entities.Book;
import com.practice.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    /* Con @Autowired las dependencias de la clase (sus atributos), son instanciados una única vez cuando se despliega
    la aplicación y se comparten por todas las instancias */
    private BookRepo bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    /*

    How it works:
    - Controlador es la puerta de ingreso al back-end, accede al Servicio.
    - Servicio accede al Repositorio.
    - Repositorio accede a la DB

    */

    @Transactional
    public void createBook(Long isbn, String title, Integer year, Integer copies, Integer idAuthor, Integer idPublisher) {

        Book book = new Book();

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(authorService.showAuthor(idAuthor));
        book.setPublicationYear(year);
        book.setPublisher(publisherService.showPublisher(idPublisher));
        book.setNumberOfCopies(copies);
        book.setNumberOfCopiesLent(0);
        book.setNumberOfCopiesAvailable(copies);
        book.setCreationDate(LocalDate.now());
        book.setLastModificationDate(LocalDate.now());
        book.setDeactivationDate(null);

        bookRepository.save(book);
    }

    @Transactional
    public void editTotalNumberOfCopies(Long isbn, Integer numberOfCopies, LocalDate lastModificationDate) {
        bookRepository.editTotalNumberOfCopies(isbn, numberOfCopies, lastModificationDate);
    }

    @Transactional
    public void editNumberOfCopiesLent(Long isbn, Integer numberOfCopiesLentUser) {
        Book book = bookRepository.getById(isbn);

        book.setNumberOfCopiesLent(book.getNumberOfCopiesLent() + numberOfCopiesLentUser);
        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - numberOfCopiesLentUser);

        bookRepository.editNumberOfCopiesLentAndAvailable(isbn, book.getNumberOfCopiesLent(), book.getNumberOfCopiesAvailable(), LocalDate.now());
    }

    @Transactional
    public void editNumberOfCopiesAvailable(Long isbn, Integer numberOfCopiesAvailableUser) {
        Book book = bookRepository.getById(isbn);

        book.setNumberOfCopiesLent(book.getNumberOfCopiesLent() - numberOfCopiesAvailableUser);
        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() + numberOfCopiesAvailableUser);

        bookRepository.editNumberOfCopiesLentAndAvailable(isbn, book.getNumberOfCopiesLent(), book.getNumberOfCopiesAvailable(), LocalDate.now());
    }

    @Transactional
    public void deactivateBook(Long isbn) {
        bookRepository.deactivateBook(isbn, LocalDate.now(), LocalDate.now());
    }

    @Transactional(readOnly = true)
    public Book showBook(Long isbn) {
        return bookRepository.getById(isbn);
    }

    @Transactional(readOnly = true)
    public List<Book> findBooks() {
        return bookRepository.searchBooks();
    }

    @Transactional(readOnly = true)
    public List<Book> findBooksByTitle(String title) {
        return bookRepository.searchBooksByTitle(title);
    }
}