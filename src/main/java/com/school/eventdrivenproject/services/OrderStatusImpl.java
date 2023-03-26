package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.entities.OrderStatus;
import com.school.eventdrivenproject.repositories.IOrderStatusRepository;
import com.school.eventdrivenproject.services.interfaces.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusImpl implements IOrderStatusService {

    @Autowired
    private IOrderStatusRepository repository;

    @Override
    public OrderStatus findOneByName(String name) {
        return repository.findOneByName(name);
    }

    @Override
    public OrderStatus findOneByNameOrCreate(String name) {
        return repository.findOrderStatusByName(name).orElseGet(() -> create(name));
    }

    @Override
    public OrderStatus create(String name) {
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setName(name);
        return repository.save(orderStatus);
    }
}
