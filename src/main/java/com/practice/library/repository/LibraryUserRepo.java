package com.practice.library.repository;

import com.practice.library.entities.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface LibraryUserRepo extends JpaRepository<LibraryUser, Integer> {

    @Modifying
    @Query("UPDATE Users u SET u.username = :username, u.userEmailAddress = :userEmailAddress, u.password = :password WHERE u.idUser = :idUser")
    void editUser(@Param("idUser") Integer idUser, @Param("username") String username,
                    @Param("userEmailAddress") String userEmailAddress, @Param("password") String password);

    @Modifying
    @Query("UPDATE Users u SET u.deactivationDate = :deactivationDate WHERE u.idUser = :idUser")
    void deactivateUser(@Param("idUser") Integer idUser, @Param("deactivationDate") LocalDate deactivationDate);

    @Query("SELECT u FROM Users u WHERE (u.deactivationDate IS NULL) AND (u.userEmailAddress = :userEmailAddress)")
    Optional<LibraryUser> searchUserByEmailAddress(@Param("userEmailAddress") String userEmailAddress);

    boolean existsByUserEmailAddress(String userEmailAddress);
}