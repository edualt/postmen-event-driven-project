package com.school.eventdrivenproject.controllers.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
public class CreateOrderRequest {

    @NotBlank
    @NotNull
    private String address;

    private String city;

    @NotBlank
    @NotNull
    private String state;


    private String postalCode;


    private String shipTo;

    private String associatedEmail;
}
