package com.practice.library.repository;

import com.practice.library.entities.Book;
import com.practice.library.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {

    @Modifying
    @Query("UPDATE Publishers p SET p.namePublisher = :namePublisher, p.lastModificationDate = :lastModificationDate WHERE idPublisher = :idPublisher")
    void editPublisherName(@Param("idPublisher") Integer idPublisher, @Param("namePublisher") String namePublisher, @Param("lastModificationDate") LocalDate lastModificationDate);

    @Modifying
    @Query("UPDATE Publishers p SET p.books = :books, p.lastModificationDate = :lastModificationDate WHERE idPublisher = :idPublisher")
    void editBooks(@Param("idPublisher") Integer idPublisher, @Param("books") List<Book> books, @Param("lastModificationDate") LocalDate lastModificationDate);

    @Modifying
    @Query("UPDATE Publishers p SET p.deactivationDate = :deactivationDate, p.lastModificationDate = :lastModificationDate WHERE idPublisher = :idPublisher")
    void deactivatePublisher(@Param("idPublisher") Integer idPublisher, @Param("deactivationDate") LocalDate deactivationDate, @Param("lastModificationDate") LocalDate lastModificationDate);

    @Query("SELECT p FROM Publishers p WHERE (p.deactivationDate IS NULL) AND (p.namePublisher LIKE :namePublisher)")
    List<Publisher> searchPublishersByName(@Param("namePublisher") String namePublisher);

    @Query("SELECT p FROM Publishers p WHERE p.deactivationDate IS NULL")
    List<Publisher> searchPublishers();
}