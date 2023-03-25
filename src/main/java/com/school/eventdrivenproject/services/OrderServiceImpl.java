package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateOrderRequest;
import com.school.eventdrivenproject.controllers.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.entities.OrderStatus;
import com.school.eventdrivenproject.repositories.IOrderRepository;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import com.school.eventdrivenproject.services.interfaces.IOrderStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository repository;

    @Autowired
    private IOrderStatusService orderStatusService;

    @Override
    public Order create(CreateOrderRequest request) {
        Order order = new Order();
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setState(request.getState());
        order.setPostalCode(request.getPostalCode());
        order.setShipTo(request.getShipTo());
        order.setAssociatedEmail(request.getAssociatedEmail());

        OrderStatus status = orderStatusService.findOneByName("RECEIVED");
        order.setStatus(status);
        return repository.save(order);
    }

    @Override
    public BaseResponse list() {
        return null;
    }

    @Override
    public Order get(Long id) {
        return findOneAndEnsureExistsById(id);
    }

    @Override
    public BaseResponse update() {
        return null;
    }

    @Override
    public BaseResponse delete(Long id) {
        return null;
    }

    @Override
    public Order findOneAndEnsureExistsById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("asd"));
    }


    private Order from(CreateOrderRequest request){
        Order order = new Order();
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setState(request.getState());
        order.setPostalCode(request.getPostalCode());
        order.setShipTo(request.getShipTo());
        order.setAssociatedEmail(request.getAssociatedEmail());

        OrderStatus status = orderStatusService.findOneByName("RECEIVED");
        order.setStatus(status);
        return order;
    }
}
