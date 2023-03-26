package com.school.eventdrivenproject.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class CreateEventRequest implements Serializable {

    private String type;
    private CreateOrderRequest order;

}
