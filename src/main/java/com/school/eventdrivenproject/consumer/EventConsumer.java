package com.school.eventdrivenproject.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.eventdrivenproject.config.RabbitMQConfiguration;
import com.school.eventdrivenproject.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.dtos.requests.UpdateEventRequest;
import com.school.eventdrivenproject.services.interfaces.IEventProcessorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventConsumer {

    @Autowired
    private IEventProcessorService eventProcessorService;

    @RabbitListener(queues = RabbitMQConfiguration.QUEUE)
    public void consumeMessageFromQueue(String event) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String eventType = event.substring(8, 25);

        if(eventType.contains("CREATE_ORDER")){
            CreateEventRequest eventRequest = objectMapper.readValue(event, CreateEventRequest.class);
            eventProcessorService.processEvent(eventRequest);
        } else{
            UpdateEventRequest eventRequest = objectMapper.readValue(event, UpdateEventRequest.class);
            eventProcessorService.processEvent(eventRequest);
        }

    }

}
