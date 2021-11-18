package com.practice.library.utilities;

import com.practice.library.exceptions.LibraryException;

import java.time.LocalDate;

public class LibraryValidations {

    public static void nullCheck(String userStringInput, String inputName) throws LibraryException {
        if(userStringInput.trim().isEmpty() || userStringInput == null) {
            throw new LibraryException(inputName + " is a mandatory field");
        }
    }

    public static void validISBNCheck(Long isbn) throws LibraryException {
        if (isbn.toString().trim().matches("^[0-9]{13}$")) {
            throw new LibraryException("Invalid ISBN! Hint: Make sure ISBN contains only numbers, and is 13 digits long");
        }
    }

    public static void validPublicationDateCheck(LocalDate userPublicationDate) throws LibraryException {
        if (userPublicationDate.isAfter(LocalDate.now())) {
            throw new LibraryException("Invalid Publication Date! Hint: Make sure that the publication date is less than the current date"); // ?
        }
    }

    public static void validNameCheck(String userName, String inputName) throws LibraryException {
        if (userName.trim().matches("^-?[0-9]+$")) { // TODO: Allow all Unicode Characters "\p{L}\p{M}*$"
            throw new LibraryException("Invalid " + inputName + "! Hint: Make sure " + inputName + "contains only letters"); // ?
        }
    }

    public static void validNumberCheck(Integer userNum, String inputName) throws LibraryException {
        if (!userNum.toString().trim().matches("^[0-9]+$")) { // .*[0-9].*
            throw new LibraryException("Invalid " + inputName + "! Hint: Make sure " + inputName + "contains only numbers");
        }
    }

    public static void validEmailCheck(String userEmail) throws LibraryException {
        if (!userEmail.trim().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
            throw new LibraryException("Invalid email! Hint: Make sure you have typed your email address correctly");
        }
    }

    public static void validPasswordCheck(String password) throws LibraryException {
        if (!password.trim().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            throw new LibraryException("Invalid password! Hint: Make sure you are complying with the password policy");
        }
    }

    // ^(?=(.*[0-9]){%d,})(?=(.*[a-z]){%d,})(?=(.*[A-Z]){%d,})(?=(.*[^0-9a-zA-Z]){%d,})(?=\S+$).{%d,}$

    /*
    Illegal repetition near index 12 ^(?=(.*[0-9]){%d,})(?=(.*[a-z]){%d,})(?=(.*[A-Z]){%d,})(?=(.*[^0-9a-zA-Z]){%d,})(?=\S+$).{%d,}$ ^
java.util.regex.PatternSyntaxException: Illegal repetition near index 12
^(?=(.*[0-9]){%d,})(?=(.*[a-z]){%d,})(?=(.*[A-Z]){%d,})(?=(.*[^0-9a-zA-Z]){%d,})(?=\S+$).{%d,}$
            ^
     */
}