package com.example.genesis.api.v1.model;

import lombok.Data;

import java.time.Instant;

@Data
public class UserError {
    private Instant timestamp = Instant.now();
    private final int status;
    private final String error;
}
