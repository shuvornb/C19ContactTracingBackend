package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.request.AddLocationDataRequest;
import com.isensor.contacttracingbackend.communication.request.EndQuarantineRequest;
import com.isensor.contacttracingbackend.communication.request.SingleAddLocationDataRequest;
import com.isensor.contacttracingbackend.db.entity.C19LocationLog;
import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.db.repository.C19LocationLogRepository;
import com.isensor.contacttracingbackend.db.repository.C19UserRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import com.isensor.contacttracingbackend.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private C19LocationLogRepository locationLogRepository;

    @Autowired
    private QuarantineService quarantineService;

    private final Logger log = LoggerFactory.getLogger(LocationService.class);

    @Transactional
    public void addLocationData(AddLocationDataRequest request) {
        if(request.locationData.isEmpty()){
            log.error("Location data empty");
            throw new BadRequestException("Location data empty");
        }

        C19User user = userRepository.findTopById(request.userId);
        if(user == null) {
            log.error("User not found");
            throw new NotFoundException("User not found");
        }

        // batch save
        List<C19LocationLog> locationLogs = new ArrayList<>();
        for(SingleAddLocationDataRequest entry: request.locationData) {
            C19LocationLog locationLog = new C19LocationLog();
            locationLog.userId = user.id;
            locationLog.latitude = entry.latitude;
            locationLog.longitude = entry.longitude;
            locationLog.createdAt = entry.createdAt;
            locationLogs.add(locationLog);
        }
        locationLogRepository.saveAll(locationLogs);
        log.info("{} location data saved for the user: {}", request.locationData.size(), request.userId);

        quarantineService.endQuarantine(new EndQuarantineRequest(request.userId, request.endTime));
    }
}
