package com.school.eventdrivenproject.controllers.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreatePackageRequest {

    @NotBlank
    @NotNull
    private String description;

    @NotBlank
    @NotNull
    private Float weight;

    @NotBlank
    @NotNull
    private Float width;

    @NotBlank
    @NotNull
    private Float height;
}
