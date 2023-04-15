package com.shop.shmakova.models;

import com.shop.shmakova.models.enums.TypeOfProducts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
@Entity
@Table(name = "master_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "availability")
    private boolean availability;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "masterClass")
    private List<MasterClassImage> images = new ArrayList<>();
    private Long previewImageId;
    private LocalDateTime dateOfCreated;
    @PrePersist
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    public void addImageToProduct(MasterClassImage image){
        image.setMasterClass(this);
        images.add(image);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", previewImageId=" + previewImageId +
                ", dateOfCreated=" + dateOfCreated +
                '}';
    }
}
