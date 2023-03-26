package com.school.eventdrivenproject.repositories;

import com.school.eventdrivenproject.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {

    boolean existsOrderByTrackingId(String trackingId);

    Order findOneByTrackingId(String trackingId);

}
