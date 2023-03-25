package com.school.eventdrivenproject.repositories;

import com.school.eventdrivenproject.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    OrderStatus findOneByName(String name);

}
