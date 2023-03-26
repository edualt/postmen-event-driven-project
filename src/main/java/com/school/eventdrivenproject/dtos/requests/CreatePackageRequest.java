package com.school.eventdrivenproject.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class CreatePackageRequest implements Serializable {

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
