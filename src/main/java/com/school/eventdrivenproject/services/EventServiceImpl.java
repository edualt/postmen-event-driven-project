package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.entities.Event;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.repositories.IEventRepository;
import com.school.eventdrivenproject.services.interfaces.IEventService;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private IEventRepository repository;

    @Autowired
    private IOrderService orderService;

    @Override
    @Transactional
    public Event create(CreateEventRequest request) throws IOException {
        Order order = orderService.create(request.getOrder(), request.getOrder().getPackageRequest());

        Event event = new Event();
        event.setType(request.getType());
        event.setOrder(order);
        event.setDate(new Date());
        return repository.save(event);
    }

    private Event from(CreateEventRequest request){
        Event event = new Event();
        event.setType(request.getType());

        return event;
    }
}
