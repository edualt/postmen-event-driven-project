package com.school.eventdrivenproject.controllers.dtos.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;

@Data
public class AuthenticationRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String password;
}