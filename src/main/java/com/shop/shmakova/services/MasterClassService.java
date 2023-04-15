package com.shop.shmakova.services;

import com.shop.shmakova.models.Image;
import com.shop.shmakova.models.MasterClass;
import com.shop.shmakova.models.MasterClassImage;
import com.shop.shmakova.models.Product;
import com.shop.shmakova.models.enums.TypeOfProducts;
import com.shop.shmakova.repositories.MasterClassRepository;
import com.shop.shmakova.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MasterClassService {
    private final MasterClassRepository masterClassRepository;
    public List<MasterClass> allMasterClasses(){
        return masterClassRepository.findAll();
    }
    public void saveMasterClass(MasterClass masterClass, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        MasterClassImage image1;
        MasterClassImage image2;
        MasterClassImage image3;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            masterClass.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2 = toImageEntity(file2);
            masterClass.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3 = toImageEntity(file3);
            masterClass.addImageToProduct(image3);
        }
        MasterClass masterClassFromDb = masterClassRepository.save(masterClass);
        masterClassFromDb.setPreviewImageId(masterClassFromDb.getImages().get(0).getId());
        masterClassRepository.save(masterClass);
    }
    public void updateMasterClass(MasterClass masterClass){
        masterClassRepository.save(masterClass);
    }
    private MasterClassImage toImageEntity(MultipartFile file) throws IOException {
        MasterClassImage image = new MasterClassImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteMasterClass(Long id){
        masterClassRepository.deleteById(id);
    }

    public MasterClass getMasterClassById(Long id){
        return masterClassRepository.findById(id).orElse(null);
    }
}
