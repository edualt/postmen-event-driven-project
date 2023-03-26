package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.dtos.requests.CreateOrderRequest;
import com.school.eventdrivenproject.dtos.requests.CreatePackageRequest;
import com.school.eventdrivenproject.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Order;

import java.io.IOException;

public interface IOrderService {
    Order create(CreateOrderRequest orderRequest, CreatePackageRequest packageRequest) throws IOException;

    BaseResponse list();

    Order get(Long id);

    Order updateStatusToInProgress(String trackingId);

    Order updateStatusToDelivered(String trackingId);

    BaseResponse delete(Long id);

    Order findOneAndEnsureExistsById(Long id);

    Order findById(Long id);
}
