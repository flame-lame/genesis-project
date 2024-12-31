package com.example.genesis.service;

public class CannotReadPersonIdListException extends RuntimeException {
    public CannotReadPersonIdListException(String message, Throwable cause) {
        super(message, cause);
    }
}
