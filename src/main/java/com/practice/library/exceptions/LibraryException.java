package com.practice.library.exceptions;

public class LibraryException extends Exception {

    private String msg;

    public LibraryException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}