package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.entities.Event;
import com.school.eventdrivenproject.entities.Order;

public interface IEventService {

    Event create(CreateEventRequest request);

}
