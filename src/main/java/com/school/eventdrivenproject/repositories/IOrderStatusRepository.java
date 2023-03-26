package com.school.eventdrivenproject.repositories;

import com.school.eventdrivenproject.entities.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IOrderStatusRepository extends JpaRepository<OrderStatus, Long> {

    OrderStatus findOneByName(String name);

    Optional<OrderStatus> findOrderStatusByName(String name);

}
