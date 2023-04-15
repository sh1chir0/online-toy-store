package com.shop.shmakova.services;

import com.shop.shmakova.models.Image;
import com.shop.shmakova.models.MasterClassImage;
import com.shop.shmakova.repositories.ImageRepository;
import com.shop.shmakova.repositories.MasterClassImageRepository;
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
public class MasterClassImageService {
    private final MasterClassImageRepository imageRepository;
    private final MasterClassService masterClassService;

    public void add(MultipartFile file, Long masterClass_id) throws IOException {
        imageRepository.save(toImageEntity(file,masterClass_id));
    }
    private MasterClassImage toImageEntity(MultipartFile file, Long masterClass_id) throws IOException {
        MasterClassImage image = new MasterClassImage();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        image.setMasterClass(masterClassService.getMasterClassById(masterClass_id));
        return image;
    }
    public List<MasterClassImage> findByMasterClassId(Long masterClass_id){
        return imageRepository.findByMasterClassId(masterClass_id);
    }

    public void remove(Long id){
        imageRepository.deleteById(id);
    }
}
