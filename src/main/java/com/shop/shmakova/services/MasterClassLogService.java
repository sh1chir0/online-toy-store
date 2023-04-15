package com.shop.shmakova.services;

import com.shop.shmakova.models.MasterClass;
import com.shop.shmakova.models.MasterClassLog;
import com.shop.shmakova.models.ProductLog;
import com.shop.shmakova.repositories.MasterClassLogRepository;
import com.shop.shmakova.repositories.ProductLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 14.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class MasterClassLogService {
    private final MasterClassLogRepository masterClassLogRepository;
    public void log(String log){
        MasterClassLog masterClassLog = new MasterClassLog();
        masterClassLog.setLog(log);
        masterClassLogRepository.save(masterClassLog);
    }
    public List<MasterClassLog> all(){
        return masterClassLogRepository.findAll();
    }

}
