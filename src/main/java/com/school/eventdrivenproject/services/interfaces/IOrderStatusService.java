package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.entities.OrderStatus;

public interface IOrderStatusService {

    OrderStatus findOneByName(String name);
}
