package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateOrderRequest;
import org.springframework.stereotype.Service;

public interface IOrderPublisher {

    void sendOrder(CreateOrderRequest request);

}
