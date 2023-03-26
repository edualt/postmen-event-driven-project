package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.dtos.requests.UpdateEventRequest;
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

        if(event instanceof CreateEventRequest eventRequest){
            System.out.println("Message received from queue: " + eventRequest.getType());
            eventService.create(eventRequest);
        }

        if(event instanceof UpdateEventRequest eventRequest){
            System.out.println("Message received from queue: " + eventRequest.getType());

            switch (eventRequest.getType()) {
                case "START_DELIVERY" -> orderService.updateStatusToInProgress(eventRequest.getTrackingId());
                case "FINISH_DELIVERY" -> orderService.updateStatusToDelivered(eventRequest.getTrackingId());
            }
        }

    }

}
