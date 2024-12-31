package com.example.genesis.service;

public class DuplicatePersonIdException extends Exception {
    public DuplicatePersonIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
