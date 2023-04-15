package com.shop.shmakova.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author sh1chiro 31.03.2023
 */
@Entity
@Data
@Table(name="orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="link")
    private String link;
    @Column(name="name")
    private String name;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="email")
    private String email;
    @Column(name="addressOfDelivery")
    private String addressOfDelivery;
    @Column(name="methodOfDelivery")
    private String methodOfDelivery;
    @Column(name="departmentOfDelivery")
    private String departmentOfDelivery;
    @Column(name="price")
    private String price;
    @Column(name="dateOfCreated")
    private LocalDateTime dateOfCreated;
    @Column(name="view")
    private String status = "Необроблено";

    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
