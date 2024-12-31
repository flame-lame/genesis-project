package com.example.genesis.controller.model;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest (
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String personId
) {}
