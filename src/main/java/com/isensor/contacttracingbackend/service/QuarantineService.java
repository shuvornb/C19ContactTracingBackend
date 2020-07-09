package com.isensor.contacttracingbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isensor.contacttracingbackend.communication.request.StartQuarantineRequest;
import com.isensor.contacttracingbackend.communication.request.UpdateHomeLocationRequest;
import com.isensor.contacttracingbackend.db.entity.C19QuarantineStatus;
import com.isensor.contacttracingbackend.db.repository.C19QuarantineStatusRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuarantineService {

    @Autowired
    private C19QuarantineStatusRepository quarantineStatusRepository;

    private final Logger log = LoggerFactory.getLogger(QuarantineService.class);

    public void startQuarantine(StartQuarantineRequest request) {
        // check if this person already in a quarantine or not
        C19QuarantineStatus alreadyActiveQuarantine = quarantineStatusRepository.findTopByUserIdAndIsActive(request.userId, true);
        if(alreadyActiveQuarantine != null) {
            log.error("This person is already in quarantine");
            throw new BadRequestException("This person is already in quarantine");
        }

        // start quarantine
        C19QuarantineStatus quarantineStatus = new C19QuarantineStatus();
        quarantineStatus.userId = request.userId;
        quarantineStatus.startTime = request.startTime;
        quarantineStatus.isActive = true;

        quarantineStatusRepository.save(quarantineStatus);
        log.info("Quarantine started successfully for userId: {}", request.userId);
    }

    public void updateHomeLocation(UpdateHomeLocationRequest request) {
        // check if this person already in a quarantine or not
        C19QuarantineStatus alreadyActiveQuarantine = quarantineStatusRepository.findTopByUserIdAndIsActive(request.userId, true);
        if(alreadyActiveQuarantine == null) {
            log.error("This person is not in quarantine");
            throw new BadRequestException("This person is not in quarantine");
        }

        // update home location

        ObjectMapper objectMapper = new ObjectMapper();
        String homeLocationJSONString = null;
        try {
            homeLocationJSONString = objectMapper.writeValueAsString(request.homeLocationData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("JSON String for Symptoms: {}", homeLocationJSONString);

        alreadyActiveQuarantine.homeLocationCoordinates = homeLocationJSONString;
        quarantineStatusRepository.save(alreadyActiveQuarantine);
        log.info("Home location updated successfully for userId: {}", request.userId);
    }
}
