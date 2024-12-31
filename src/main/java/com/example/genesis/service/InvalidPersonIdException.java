package com.example.genesis.service;

public class InvalidPersonIdException extends RuntimeException {
  public InvalidPersonIdException(String message) {
    super(message);
  }
}
