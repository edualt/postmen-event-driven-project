package com.school.eventdrivenproject.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.eventdrivenproject.config.RabbitMQConfiguration;
import com.school.eventdrivenproject.controllers.dtos.requests.CreateEventRequest;
import com.school.eventdrivenproject.controllers.dtos.requests.UpdateEventRequest;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.services.interfaces.IEventProcessorService;
import com.school.eventdrivenproject.services.interfaces.IEventService;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventConsumer {

    @Autowired
    private IEventService eventService;

    @Autowired
    private IOrderService orderService;

    @RabbitListener(queues = "received_order_queue")
    public void inProgressListener(CreateEventRequest request) throws IOException {
        System.out.println(request.getType());
        eventService.create(request);
    }

    @RabbitListener(queues = "in_progress_queue")
    public void receivedOrderListener(UpdateEventRequest request) {
        eventService.create(request, request.getTrackingId());
        orderService.updateStatusToInProgress(request.getTrackingId());;
    }

    @RabbitListener(queues = "finished_order_queue")
    public void finishedOrderListener(UpdateEventRequest request){
        eventService.create(request, request.getTrackingId());
        orderService.updateStatusToDelivered(request.getTrackingId());
    }
}
