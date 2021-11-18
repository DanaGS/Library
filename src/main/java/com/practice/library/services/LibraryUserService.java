package com.practice.library.services;

import com.practice.library.entities.LibraryUser;
import com.practice.library.exceptions.LibraryException;
import com.practice.library.repository.LibraryUserRepo;
import com.practice.library.utilities.LibraryValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class LibraryUserService implements UserDetailsService {

    @Autowired
    private LibraryUserRepo userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(String username, String userEmailAddress, String password) throws LibraryException {
        if (userRepository.existsByUserEmailAddress(userEmailAddress)) {
            throw new LibraryException("User with email address " + userEmailAddress + " already exists! Please try a different address");
        }

        userValidations(username, userEmailAddress, password);

        LibraryUser libraryUser = new LibraryUser();

        libraryUser.setUsername(username);
        libraryUser.setUserEmailAddress(userEmailAddress);
        libraryUser.setPassword(passwordEncoder.encode(password));

        userRepository.save(libraryUser);
    }

    public void userValidations(String username, String userEmailAddress, String password) throws LibraryException {

        LibraryValidations.nullCheck(username, "Username");
        LibraryValidations.nullCheck(userEmailAddress, "Email Address");
        LibraryValidations.nullCheck(password, "Password");
        LibraryValidations.validEmailCheck(userEmailAddress);
        LibraryValidations.validPasswordCheck(password);
    }

    @Override // Checks if user exists in DB
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LibraryUser libraryUser = userRepository.searchUserByEmailAddress(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(libraryUser.getUserEmailAddress(), libraryUser.getPassword(), Collections.emptyList());
    }
}