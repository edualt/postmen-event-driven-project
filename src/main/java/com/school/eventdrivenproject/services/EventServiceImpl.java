package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.controllers.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Event;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.repositories.IEventRepository;
import com.school.eventdrivenproject.services.interfaces.IEventService;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private IEventRepository repository;

    @Autowired
    private IOrderService orderService;


    @Override
    @Transactional
    public Event create(CreateEventRequest request) {
        Order order = orderService.create(request.getOrder());
        Event event = new Event();
        event.setType(request.getType());
        event.setOrder(order);
        return repository.save(event);
    }

    private Event from(CreateEventRequest request){
        Event event = new Event();
        event.setType(request.getType());

        return event;
    }
}
