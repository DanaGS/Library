package com.practice.library.services;

import com.practice.library.entities.Author;
import com.practice.library.entities.Book;
import com.practice.library.entities.Publisher;
import com.practice.library.repository.AuthorRepo;
import com.practice.library.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepository;

    @Autowired
    private BookRepo bookRepository;

    @Transactional
    public void addAuthor(String name) {
        Author author = new Author();
        List<Book> books = new ArrayList<Book>();
        author.setNameAuthor(name);
        author.setBooks(books);
        author.setCreationDate(LocalDate.now());
        author.setLastModificationDate(LocalDate.now());
        author.setDeactivationDate(null);

        authorRepository.save(author);
    }


    @Transactional
    public void editAuthorName(Integer idAuthor, String nameAuthor) {
        authorRepository.editAuthorName(idAuthor, nameAuthor, LocalDate.now());
    }

    @Transactional
    public void addAuthorBooks(Integer idAuthor, Book book) {
        List<Book> books = authorRepository.getById(idAuthor).getBooks();
        books.add(book);
        authorRepository.editBooks(idAuthor, books, LocalDate.now());
    }

    @Transactional
    public void removeAuthorBooks(Integer idAuthor, Book book) {
        List<Book> books = authorRepository.getById(idAuthor).getBooks();
        books.remove(book);
        bookRepository.deactivateBook(book.getIsbn(), LocalDate.now(), LocalDate.now());
        authorRepository.editBooks(idAuthor, books, LocalDate.now());
    }

    @Transactional
    public void deactivateAuthor(Integer idAuthor) {
        authorRepository.deactivateAuthor(idAuthor, LocalDate.now(), LocalDate.now());
        //authorRepository.deleteById(idAuthor);
    }

    @Transactional(readOnly = true)
    public Author showAuthor(Integer idAuthor) {
        return authorRepository.getById(idAuthor);
    }

    @Transactional(readOnly = true)
    public List<Author> findAuthors() {
        return authorRepository.searchAuthors();
    }

    @Transactional(readOnly = true)
    public List<Author> findAuthorsByName(String name) {
        return authorRepository.searchAuthorsByName(name);
    }
}