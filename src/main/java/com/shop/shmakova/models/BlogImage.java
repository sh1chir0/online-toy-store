package com.shop.shmakova.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sh1chiro 04.04.2023
 */
@Entity
@Table(name = "blog_images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFileName")
    private String originalFileName;
    @Column(name = "size")
    private Long size;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "bytes", columnDefinition = "MEDIUMBLOB")
    private byte[] bytes;
    @Column(name = "post_id")
    private Long postId;
}