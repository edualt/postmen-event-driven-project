package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.controllers.dtos.requests.UpdateEventRequest;
import com.school.eventdrivenproject.services.interfaces.IEventProcessorService;
import com.school.eventdrivenproject.services.interfaces.IEventService;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventProcessorImpl implements IEventProcessorService {

    @Autowired
    private IEventService eventService;

    @Autowired
    private IOrderService orderService;

    @Override
    public void processEvent(Object event) throws IOException {
        System.out.println("object received: " + event);

        if(event instanceof CreateEventRequest){
            CreateEventRequest eventRequest = (CreateEventRequest) event;
            System.out.println("Message received from queue: " + eventRequest.getType());
            eventService.create(eventRequest);
        }

        if(event instanceof UpdateEventRequest){
            UpdateEventRequest eventRequest = (UpdateEventRequest) event;
            System.out.println("Message received from queue: " + eventRequest.getType());

            switch (eventRequest.getType()) {
                case "START_DELIVERY":
                    eventService.create(eventRequest, eventRequest.getTrackingId());
                    orderService.updateStatusToInProgress(eventRequest.getTrackingId());
                    break;
                case "FINISH_DELIVERY":
                    eventService.create(eventRequest, eventRequest.getTrackingId());
                    orderService.updateStatusToDelivered(eventRequest.getTrackingId());
                    break;
            }
        }

    }

}
