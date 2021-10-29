package com.practice.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity (name = "Books")
@Table (name = "Books")
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    // TODO: REMEMBER TO ADD "DESCRIPTION" FIELD TO BOOKS. ONLY SHOW "DESCRIPTION" WHEN USER CLICKS ON A BOOK
    // TODO: ALLOW ADMIN TO UPLOAD AN IMAGE OF THE COVER OF THE BOOK. THIS IMAGE SHOULD LOAD EVERYTIME A BOOK IS SHOWN
    // TODO: CREATE AN ENUM WITH DIFFERENT GENRES. THE READER SHOULD BE ABLE TO BROWSE BY GENRE, AND EACH BOOK SHOULD HAVE AT LEAST ONE GENRE ASSIGNED
    // TODO: WHEN SHOWING A BOOK, THE AUTHOR AND PUBLISHER SHOULD BE A HYPERLINK

    // TODO: ALLOW USER TO SEARCH BY TITLE, ISBN. AUTHOR, PUBLISHER AND GENRE
    // TODO: CREATE VIEWS FOR BOOKS

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    @Getter @Setter
    private Long isbn;

    @Column(nullable = false)
    @Getter @Setter
    private String title;

    @Getter @Setter
    private Integer publicationYear;

    @Column(nullable = false)
    @Getter @Setter
    private Integer numberOfCopies;

    @Column(nullable = false)
    @Getter @Setter
    private Integer numberOfCopiesLent;

    @Column(nullable = false)
    @Getter @Setter
    private Integer numberOfCopiesAvailable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Getter @Setter
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Getter @Setter
    private Publisher publisher;

    // https://www.baeldung.com/jpa-java-time
    @Column(columnDefinition = "DATE", nullable = false)
    @Getter @Setter
    private LocalDate creationDate;

    @Column(columnDefinition = "DATE")
    @Getter @Setter
    private LocalDate deactivationDate;

    @Column(columnDefinition = "DATE", nullable = false)
    @Getter @Setter
    private LocalDate lastModificationDate;
}
