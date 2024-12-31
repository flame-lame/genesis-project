package com.example.genesis.controller.model;

import java.util.UUID;

public record GetUserDetailResponse (
        Long id,
        String name,
        String surname,
        String personId,
        UUID uuid
) {}
