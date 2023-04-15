package com.shop.shmakova.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author sh1chiro 04.04.2023
 */
@Entity
@Data
@Table(name = "blog")
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="title")
    private String title;
    @Column(name = "text", length = 3000)
    private String text;
    private LocalDateTime dateOfCreated;
    @Column(name = "image_id")
    private Long imageId;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }
}
