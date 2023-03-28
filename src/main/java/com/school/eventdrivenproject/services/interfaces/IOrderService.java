package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateOrderRequest;
import com.school.eventdrivenproject.controllers.dtos.requests.CreatePackageRequest;
import com.school.eventdrivenproject.controllers.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Order;

import java.io.IOException;

public interface IOrderService {
    Order create(CreateOrderRequest orderRequest, CreatePackageRequest packageRequest) throws IOException;

    BaseResponse list();

    Order get(Long id);

    Order updateStatusToInProgress(String trackingId);

    Order updateStatusToDelivered(String trackingId);

    Order findOneAndEnsureExistsById(Long id);

    Order findByTrackingId(String trackingId);
}
