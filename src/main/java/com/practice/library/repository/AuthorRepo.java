package com.practice.library.repository;

import com.practice.library.entities.Author;
import com.practice.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

    @Modifying
    @Query("UPDATE Authors a SET a.nameAuthor = :nameAuthor, a.lastModificationDate = :lastModificationDate WHERE a.idAuthor = :idAuthor")
    void editAuthorName(@Param("idAuthor") Integer idAuthor, @Param("nameAuthor") String nameAuthor, @Param("lastModificationDate") LocalDate lastModificationDate);

    @Modifying
    @Query("UPDATE Authors a SET a.books = :books, a.lastModificationDate = :lastModificationDate WHERE a.idAuthor = :idAuthor")
    void editBooks(@Param("idAuthor") Integer idAuthor, @Param("books") List<Book> books, @Param("lastModificationDate") LocalDate lastModificationDate);

    @Modifying
    @Query("UPDATE Authors a SET a.deactivationDate = :deactivationDate, a.lastModificationDate = :lastModificationDate WHERE a.idAuthor = :idAuthor")
    void deactivateAuthor(@Param("idAuthor") Integer idAuthor, @Param("deactivationDate") LocalDate deactivationDate, @Param("lastModificationDate") LocalDate lastModificationDate);

    @Query("SELECT a FROM Authors a WHERE (a.deactivationDate IS NULL) AND (a.nameAuthor LIKE %:nameAuthor%)")
    List<Author> searchAuthorsByName(@Param("nameAuthor") String nameAuthor);

    @Query("SELECT a FROM Authors a WHERE a.deactivationDate IS NULL")
    List<Author> searchAuthors();
}