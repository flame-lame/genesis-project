package com.example.genesis.api.v1.model;

public record UpdateUserRequest(
        Long id,
        String name,
        String surname
) {
}
