package com.school.eventdrivenproject.controllers;

import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.services.interfaces.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@CrossOrigin
public class OrderController {

    @Autowired
    private IOrderService service;

    @GetMapping("/{trackingId}")
    public Order get(@PathVariable String trackingId){
        return service.findByTrackingId(trackingId);
    }
}
