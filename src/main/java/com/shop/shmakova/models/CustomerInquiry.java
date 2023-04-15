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
@Table(name = "customer_inquiry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerInquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name = "email")
    private String email;
    @Column(name="text", length = 2000)
    private String text;
    @Column(name="view")
    private boolean view;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
