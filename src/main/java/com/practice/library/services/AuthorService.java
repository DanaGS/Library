package com.practice.library.services;

import com.practice.library.entities.Author;
import com.practice.library.entities.Book;
import com.practice.library.exceptions.LibraryException;
import com.practice.library.repository.AuthorRepo;
import com.practice.library.utilities.LibraryValidations;
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
    public void addAuthor(String name, String aboutAuthor) throws LibraryException {

        authorValidations(name, aboutAuthor);

        Author author = new Author();
        List<Book> books = new ArrayList<>();

        author.setNameAuthor(name);
        author.setBooks(books);
        author.setDeactivationDate(null);
        author.setAboutAuthor(descriptionService.createDescription(aboutAuthor));

        authorRepository.save(author);
    }

    public void authorValidations(String name, String aboutAuthor) throws LibraryException {

        LibraryValidations.nullCheck(name, "Author Name");
        LibraryValidations.nullCheck(aboutAuthor, "Author Bio");
        LibraryValidations.validNameCheck(name, "Author Name");
    }

    @Transactional
    public void editAuthor(Integer idAuthor, String nameAuthor, Integer idDescription, String aboutAuthor) throws LibraryException {
        LibraryValidations.nullCheck(idAuthor.toString(), "IdAuthor");
        LibraryValidations.validNumberCheck(idAuthor, "Id Author");

        if (findAuthorById(idAuthor) != null) {
            authorValidations(nameAuthor, aboutAuthor);

            descriptionService.editDescription(idDescription, aboutAuthor);
            authorRepository.editAuthor(idAuthor, nameAuthor);
        } else {
            throw new LibraryException("Author does not exist!");
        }
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