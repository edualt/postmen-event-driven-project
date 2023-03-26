package com.school.eventdrivenproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "packages")
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float weight;
    private Float width;
    private Float height;

    @OneToOne(mappedBy = "orderPackage")
    private Order order;

}
