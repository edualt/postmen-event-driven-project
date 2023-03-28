package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateOrderRequest;

public interface IOrderPublisher {

    void sendOrder(CreateOrderRequest request);

}
