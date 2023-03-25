package com.school.eventdrivenproject.controllers.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateEventRequest {

    private String type;
    private CreateOrderRequest order;

}
