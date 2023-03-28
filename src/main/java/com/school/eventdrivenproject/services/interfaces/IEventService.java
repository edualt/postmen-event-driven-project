package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.controllers.dtos.requests.UpdateEventRequest;
import com.school.eventdrivenproject.entities.Event;

import java.io.IOException;

public interface IEventService {

    Event create(CreateEventRequest request) throws IOException;

    Event create(UpdateEventRequest request, String trackingId);

}
