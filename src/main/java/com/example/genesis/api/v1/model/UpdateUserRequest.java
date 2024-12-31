package com.example.genesis.controller.model;

public record UpdateUserRequest (
    Long id,
    String name,
    String surname
) {}
