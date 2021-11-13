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
    private BookRepo bookRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private DescriptionService descriptionService;

    /*

    How it works:
    - Controlador es la puerta de ingreso al back-end, accede al Servicio.
    - Servicio accede al Repositorio.
    - Repositorio accede a la DB

    */

    @Transactional
    public void createBook(Long isbn, String title, Integer pages, LocalDate publicationDate, Integer numberOfCopies,
                           Integer idAuthor, Integer idPublisher, String bookDescription) {

        Book book = new Book();

        book.setIsbn(isbn);
        book.setTitle(title);
        book.setAuthor(authorService.findAuthorById(idAuthor));
        book.setPublicationDate(publicationDate);
        book.setPages(pages);
        book.setPublisher(publisherService.findPublisherById(idPublisher));
        book.setNumberOfCopies(numberOfCopies);
        book.setNumberOfCopiesLent(0);
        book.setNumberOfCopiesAvailable(numberOfCopies);
        book.setDeactivationDate(null);
        book.setBookDescription(descriptionService.createDescription(bookDescription));

        bookRepository.save(book);
    }

    @Transactional
    public void editBook(Long isbn, String title, Integer pages, LocalDate publicationDate, Integer numberOfCopies,
                         Integer idAuthor, Integer idPublisher, Integer idDescription, String bookDescription) {

        descriptionService.editDescription(idDescription, bookDescription);
        bookRepository.editBook(isbn, title, pages, publicationDate, numberOfCopies, idAuthor, idPublisher);
    }

    /*

    NOT UTILIZED YET

    @Transactional
    public void editTotalNumberOfCopies(Long isbn, Integer numberOfCopies) {
        bookRepository.editTotalNumberOfCopies(isbn, numberOfCopies);
    }

    @Transactional
    public void editNumberOfCopiesLent(Long isbn, Integer numberOfCopiesLentUser) {
        Book book = bookRepository.getById(isbn);

        book.setNumberOfCopiesLent(book.getNumberOfCopiesLent() + numberOfCopiesLentUser);
        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() - numberOfCopiesLentUser);

        bookRepository.editNumberOfCopiesLentAndAvailable(isbn, book.getNumberOfCopiesLent(), book.getNumberOfCopiesAvailable());
    }

    @Transactional
    public void editNumberOfCopiesAvailable(Long isbn, Integer numberOfCopiesAvailableUser) {
        Book book = bookRepository.getById(isbn);

        book.setNumberOfCopiesLent(book.getNumberOfCopiesLent() - numberOfCopiesAvailableUser);
        book.setNumberOfCopiesAvailable(book.getNumberOfCopiesAvailable() + numberOfCopiesAvailableUser);

        bookRepository.editNumberOfCopiesLentAndAvailable(isbn, book.getNumberOfCopiesLent(), book.getNumberOfCopiesAvailable());
    }

     */

    @Transactional
    public void deactivateBook(Long isbn) {
        descriptionService.deactivateDescription(bookRepository.getById(isbn).getBookDescription().getIdDescription());
        bookRepository.deactivateBook(isbn, LocalDate.now());
    }

    @Transactional(readOnly = true)
    public Book findBookById(Long isbn) {
        return bookRepository.getById(isbn);
    }

    @Transactional(readOnly = true)
    public Book showBook(Long isbn) {
        Book book =  bookRepository.getById(isbn);
        book.setBookDescription(book.getBookDescription());
        return book;
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