package com.practice.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity (name = "Publishers")
@Table (name = "Publishers")
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    // TODO: REMEMBER TO ADD "ABOUT" FIELD TO PUBLISHERS. ONLY SHOW "ABOUT" WHEN USER CLICKS ON AN PUBLISHER
    // TODO: ALLOW ADMIN TO UPLOAD AN IMAGE OF THE PUBLISHER LOGO.
    // TODO: WHEN PRESENTING A PUBLISHER, THE USER SHOULD BE ABLE TO SEE THE COLLECTION OF BOOKS PUBLISHED
    // TODO: WHEN EDITING A PUBLISHER, THE ADMIN SHOULD BE ABLE TO ADD OR DELETE BOOKS FOR THE LIST

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @Getter @Setter
    private Integer idPublisher;

    @Column(nullable = false)
    @Getter @Setter
    private String namePublisher;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    @Getter @Setter
    private List<Book> books;

    @Column(columnDefinition = "DATE", nullable = false)
    @Getter @Setter
    private LocalDate creationDate;

    @Column(columnDefinition = "DATE")
    @Getter @Setter
    private LocalDate  deactivationDate;

    @Column(columnDefinition = "DATE", nullable = false)
    @Getter @Setter
    private LocalDate lastModificationDate;
}