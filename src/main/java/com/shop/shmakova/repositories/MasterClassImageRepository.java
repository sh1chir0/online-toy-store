package com.shop.shmakova.repositories;

import com.shop.shmakova.models.Image;
import com.shop.shmakova.models.MasterClassImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
public interface MasterClassImageRepository  extends JpaRepository<MasterClassImage, Long> {
    List<MasterClassImage> findByMasterClassId(Long product_id);
}
