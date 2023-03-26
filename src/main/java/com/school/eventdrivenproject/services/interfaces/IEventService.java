package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.entities.Event;

import java.io.IOException;

public interface IEventService {

    Event create(CreateEventRequest request) throws IOException;

}
