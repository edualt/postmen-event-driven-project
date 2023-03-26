package com.school.eventdrivenproject.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateEventRequest {

    private String type;
    private String trackingId;

}
