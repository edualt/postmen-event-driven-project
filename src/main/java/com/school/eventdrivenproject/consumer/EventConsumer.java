package com.school.eventdrivenproject.consumer;

import com.school.eventdrivenproject.config.MessagingConfig;
import com.school.eventdrivenproject.controllers.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.entities.Event;
import com.school.eventdrivenproject.services.interfaces.IEventService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {

    @Autowired
    private IEventService eventService;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public String consumeMessageFromQueue(CreateEventRequest eventRequest){
        System.out.println("Message received from queue: " + eventRequest.getType());
        executeFunction(eventRequest.getType(), eventRequest);
        return "Event: " + eventRequest.getType();
    }


    public Event executeFunction(String type, CreateEventRequest event) {
        switch (type) {
            case "CREATE_ORDER":
                return eventService.create(event);
            case "START_DELIVERY":
                return null;
            default:
                return null;
        }
    }
}
