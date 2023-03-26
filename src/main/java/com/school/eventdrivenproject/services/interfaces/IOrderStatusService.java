package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.entities.OrderStatus;

public interface IOrderStatusService {

    OrderStatus create(String name);

    OrderStatus findOneByName(String name);

    OrderStatus findOneByNameOrCreate(String name);

}
