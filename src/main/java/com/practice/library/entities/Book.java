package com.practice.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity (name = "Books")
@Table (name = "Books")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Book {

    // TODO: REMEMBER TO ADD "DESCRIPTION" FIELD TO BOOKS. ONLY SHOW "DESCRIPTION" WHEN USER CLICKS ON A BOOK
    // TODO: ALLOW ADMIN TO UPLOAD AN IMAGE OF THE COVER OF THE BOOK. THIS IMAGE SHOULD LOAD EVERYTIME A BOOK IS SHOWN
    // TODO: CREATE AN ENUM WITH DIFFERENT GENRES. THE READER SHOULD BE ABLE TO BROWSE BY GENRE, AND EACH BOOK SHOULD HAVE AT LEAST ONE GENRE ASSIGNED
    // TODO: WHEN SHOWING A BOOK, THE AUTHOR AND PUBLISHER SHOULD BE A HYPERLINK

    // TODO: ALLOW USER TO SEARCH BY TITLE, ISBN. AUTHOR, PUBLISHER AND GENRE
    // TODO: CREATE VIEWS FOR BOOKS

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long isbn;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate publicationDate;

    @JoinColumn(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer numberOfCopies;

    @Column(nullable = false)
    private Integer numberOfCopiesLent;

    @Column(nullable = false)
    private Integer numberOfCopiesAvailable;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Publisher publisher;

    // https://www.baeldung.com/jpa-java-time
    @CreatedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate creationDate;

    @Column(columnDefinition = "DATE")
    private LocalDate deactivationDate;

    @LastModifiedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate lastModificationDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Description bookDescription;

    /*

    @Column(nullable = false)
    private String bookCoverImgPath;

    @Enumerated(EnumType.ORDINAL)
    private List<Genre> genres;

    */
}