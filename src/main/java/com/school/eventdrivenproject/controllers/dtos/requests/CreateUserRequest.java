package com.school.eventdrivenproject.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUserRequest {

    private String email;
    private String password;

}
