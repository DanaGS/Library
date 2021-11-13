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

@Entity (name = "Publishers")
@Table (name = "Publishers")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Publisher {

    // TODO: ALLOW ADMIN TO UPLOAD AN IMAGE OF THE PUBLISHER LOGO.
    // TODO: WHEN PRESENTING A PUBLISHER, THE USER SHOULD BE ABLE TO SEE THE COLLECTION OF BOOKS PUBLISHED
    // TODO: WHEN EDITING A PUBLISHER, THE ADMIN SHOULD BE ABLE TO ADD OR DELETE BOOKS FOR THE LIST

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublisher;

    @Column(nullable = false)
    private String namePublisher;

    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    private List<Book> books;

    @CreatedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate creationDate;

    @Column(columnDefinition = "DATE")
    private LocalDate  deactivationDate;

    @LastModifiedDate
    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate lastModificationDate;

    @OneToOne(fetch = FetchType.LAZY)
    private Description aboutPublisher;

    /*

    @Column(nullable = false)
    private String publisherLogoImgPath;

    */
}