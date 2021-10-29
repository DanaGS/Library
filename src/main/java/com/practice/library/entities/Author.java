package com.practice.library.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "Authors")
@Table(name = "Authors")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
/* @SQLDelete(sql = "UPDATE Author SET deactivationDate = '' WHERE idAuthor = ?")
@Where(clause = "deactivationDate IS NULL") */
public class Author {

    // TODO: REMEMBER TO ADD "ABOUT" FIELD TO AUTHORS. ONLY SHOW "ABOUT" WHEN USER CLICKS ON AN AUTHOR
    // TODO: ALLOW ADMIN TO UPLOAD AN IMAGE OF THE AUTHOR.
    // TODO: EACH AUTHOR CAN HAVE MORE THAN ONE GENRE ASSIGNED
    // TODO: WHEN PRESENTING AN AUTHOR, THE USER SHOULD BE ABLE TO SEE A COLLECTION OF BOOKS WRITTEN BY THAT AUTHOR
    // TODO: WHEN EDITING AN AUTHOR, THE ADMIN SHOULD BE ABLE TO ADD OR DELETE BOOKS FOR THE LIST

    // TODO: ADMIN SHOULD BE ABLE TO SEE CREATION AND MODIFICATION DATES FOR ALL OBJECTS

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer idAuthor;

    @Column(nullable = false)
    private String nameAuthor;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private List<Book> books;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate creationDate;

    @Column(columnDefinition = "DATE")
    private LocalDate  deactivationDate;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate lastModificationDate;
}