package com.shop.shmakova.services;

import com.shop.shmakova.models.UserLog;
import com.shop.shmakova.repositories.UserLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sh1chiro 03.04.2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserLogService {
    private final UserLogRepository userLogRepository;
    public List<UserLog> all(){
        return  userLogRepository.findAll();
    }
    public void log(String log){
        UserLog userLog = new UserLog();
        userLog.setLog(log);
        userLogRepository.save(userLog);
    }
}
