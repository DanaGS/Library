package com.practice.library.services;

import com.practice.library.entities.Author;
import com.practice.library.entities.Book;
import com.practice.library.repository.AuthorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepo authorRepository;

    @Autowired
    private DescriptionService descriptionService;

    @Transactional
    public void addAuthor(String name, String aboutAuthor) {

        Author author = new Author();
        List<Book> books = new ArrayList<>();

        author.setNameAuthor(name);
        author.setBooks(books);
        author.setDeactivationDate(null);
        author.setAboutAuthor(descriptionService.createDescription(aboutAuthor));

        authorRepository.save(author);
    }

    @Transactional
    public void editAuthor(Integer idAuthor, String nameAuthor, Integer idDescription, String aboutAuthor) {

        Author author = findAuthorById(idAuthor);

        /*

        if (author == null) {
            throw new LibraryException("Author does not exist!");
        }

        */

        descriptionService.editDescription(idDescription, aboutAuthor);
        authorRepository.editAuthor(idAuthor, nameAuthor);
    }

    @Transactional
    public void deactivateAuthor(Integer idAuthor, Integer idDescription) {
        authorRepository.deactivateAuthor(idAuthor, LocalDate.now());
        descriptionService.deactivateDescription(idDescription);
        //authorRepository.deleteById(idAuthor);
    }

    @Transactional(readOnly = true)
    public Author findAuthorById(Integer idAuthor) {
        Optional<Author> optionalAuthor = authorRepository.findById(idAuthor);
        return optionalAuthor.orElse(null);
    }

    @Transactional(readOnly = true)
    public Author showAuthor(Integer idAuthor) {
        Author author = authorRepository.getById(idAuthor);
        author.setAboutAuthor(author.getAboutAuthor());
        author.setBooks(author.getBooks());
        return author;
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