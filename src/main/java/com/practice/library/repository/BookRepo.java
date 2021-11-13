package com.practice.library.repository;

import com.practice.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> { // JpaRepository<EntityName, EntityIDType>

    /*
    JpaRepository extends from PagingAndSortingRepository, CrudRepository, Repository.
    Contains methods for CRUD (Create, Read, Update and Delete) operations
    */

    @Modifying
    @Query("UPDATE Books b SET b.title = :title, b.pages = :pages, b.publicationDate = :publicationDate, b.numberOfCopies = :numberOfCopies, " +
            "b.author = :idAuthor, b.publisher = :idPublisher WHERE b.isbn = :isbn")
    void editBook(@Param("isbn") Long isbn, @Param("title") String title, @Param("pages") Integer pages, @Param("publicationDate")
                  LocalDate publicationDate, @Param("numberOfCopies") Integer numberOfCopies, @Param("idAuthor") Integer idAuthor,
                  @Param("idPublisher") Integer idPublisher);

    @Modifying
    @Query("UPDATE Books b SET b.numberOfCopies = :numberOfCopies WHERE b.isbn = :isbn") // Remember to update "numberOfCopiesAvailable"
    void editTotalNumberOfCopies(@Param("isbn") Long isbn, @Param("numberOfCopies") Integer numberOfCopies);

    @Modifying
    @Query("UPDATE Books b SET b.numberOfCopiesLent = :numberOfCopiesLent, b.numberOfCopiesAvailable = :numberOfCopiesAvailable WHERE b.isbn = :isbn")
    void editNumberOfCopiesLentAndAvailable(@Param("isbn") Long isbn,
                                            @Param("numberOfCopiesLent") Integer numberOfCopiesLent,
                                            @Param("numberOfCopiesAvailable") Integer numberOfCopiesAvailable);

    @Modifying
    @Query("UPDATE Books b SET b.deactivationDate = :deactivationDate WHERE b.isbn = :isbn")
    void deactivateBook(@Param("isbn") Long isbn, @Param("deactivationDate") LocalDate deactivationDate);

    @Query("SELECT b FROM Books b WHERE (b.deactivationDate IS NULL) AND (b.title LIKE :title)")
    List<Book> searchBooksByTitle(@Param("title") String title);

    @Query("SELECT b FROM Books b WHERE b.deactivationDate IS NULL")
    List<Book> searchBooks();

    /*
    * List<Autor> findByAltaTrue();
    * List<Autor> findByAltaTrueOrderByNombreAsc();
    * Autor findByNombreIgnoreCase(String nombre);
    */
}