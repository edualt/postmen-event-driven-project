package com.school.eventdrivenproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String type;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}
