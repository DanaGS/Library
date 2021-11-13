package com.practice.library.repository;

import com.practice.library.entities.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DescriptionRepo extends JpaRepository<Description, Integer> {

    @Modifying
    @Query("UPDATE Descriptions d SET d.description = :description WHERE d.idDescription = :idDescription")
    void editDescription(@Param("idDescription") Integer idDescription, @Param("description") String description);

    @Modifying
    @Query("UPDATE Descriptions d SET d.deactivationDate = :deactivationDate WHERE d.idDescription = :idDescription")
    void deactivateDescription(@Param("idDescription") Integer idDescription, @Param("deactivationDate") LocalDate deactivationDate);
}
