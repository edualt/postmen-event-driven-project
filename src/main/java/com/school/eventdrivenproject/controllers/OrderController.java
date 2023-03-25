package com.school.eventdrivenproject.controllers;

import com.school.eventdrivenproject.controllers.dtos.requests.CreateOrderRequest;
import com.school.eventdrivenproject.controllers.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@CrossOrigin

public class OrderController {

    @Autowired
    private IOrderService service;

    @PostMapping
    public ResponseEntity<Order> create(@RequestBody CreateOrderRequest request){
        Order order = service.create(request);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
