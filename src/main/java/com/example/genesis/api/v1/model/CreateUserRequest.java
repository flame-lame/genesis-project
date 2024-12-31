package com.example.genesis.api.v1.model;

import com.example.genesis.validator.ValidPersonId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @Schema(example = "John")
        @NotBlank
        String name,
        @Schema(example = "Doe")
        @NotBlank
        String surname,
        @Schema(enumAsRef = true, example = "s**********z")
        @ValidPersonId
        @NotBlank
        String personId
) {
}
