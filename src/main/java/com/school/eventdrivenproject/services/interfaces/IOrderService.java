package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateOrderRequest;
import com.school.eventdrivenproject.controllers.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Order;

public interface IOrderService {
    Order create(CreateOrderRequest request);

    BaseResponse list();

    Order get(Long id);

    BaseResponse update();

    BaseResponse delete(Long id);

    Order findOneAndEnsureExistsById(Long id);

    Order findById(Long id);
}
