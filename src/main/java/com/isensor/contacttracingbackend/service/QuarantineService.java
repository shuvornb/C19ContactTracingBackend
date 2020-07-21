package com.isensor.contacttracingbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isensor.contacttracingbackend.communication.request.*;
import com.isensor.contacttracingbackend.communication.response.FetchQuarantineSummaryResponse;
import com.isensor.contacttracingbackend.communication.response.StartQuarantineResponse;
import com.isensor.contacttracingbackend.db.entity.C19LocationLog;
import com.isensor.contacttracingbackend.db.entity.C19QuarantineStatus;
import com.isensor.contacttracingbackend.db.repository.C19LocationLogRepository;
import com.isensor.contacttracingbackend.db.repository.C19QuarantineStatusRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuarantineService {

    @Autowired
    private C19QuarantineStatusRepository quarantineStatusRepository;

    @Autowired
    private C19LocationLogRepository locationLogRepository;

    @Value("${quarantine.period}")
    private Integer quarantinePeriod;

    private final Logger log = LoggerFactory.getLogger(QuarantineService.class);

    public StartQuarantineResponse startQuarantine(StartQuarantineRequest request) {
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
        StartQuarantineResponse response = new StartQuarantineResponse();
        response.message = "Quarantine started successfully";
        response.userId = request.userId;
        response.quarantinePeriod = quarantinePeriod;
        return response;
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

    public void endQuarantine(EndQuarantineRequest request) {
        // check if this person already in a quarantine or not
        C19QuarantineStatus alreadyActiveQuarantine = quarantineStatusRepository.findTopByUserIdAndIsActive(request.userId, true);
        if(alreadyActiveQuarantine == null) {
            log.error("This person is not in quarantine");
            throw new BadRequestException("This person is not in quarantine");
        }

        alreadyActiveQuarantine.endTime = request.endTime;
        alreadyActiveQuarantine.isActive = false;
        quarantineStatusRepository.save(alreadyActiveQuarantine);
        log.info("End time updated successfully for userId: {}", request.userId);
    }

    public FetchQuarantineSummaryResponse fetchQuarantineSummary(Long userId) {
        // check if this person was in a quarantine or not
        C19QuarantineStatus quarantineStatus = quarantineStatusRepository.findTopByUserIdAndIsActiveOrderByStartTimeDesc(userId, false);
        if(quarantineStatus == null) {
            log.error("This person was never in a quarantine");
            throw new BadRequestException("This person was never in a quarantine");
        }

        FetchQuarantineSummaryResponse response = new FetchQuarantineSummaryResponse();
        response.startTime = quarantineStatus.startTime;
        response.endTime = quarantineStatus.endTime;
        List<Location> homeLocationCoordinates = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            homeLocationCoordinates = objectMapper.readValue(quarantineStatus.homeLocationCoordinates, new TypeReference<List<Location>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.homeLocationData = homeLocationCoordinates;

        List<C19LocationLog> locationLogList = locationLogRepository.findByUserIdAndCreatedAtGreaterThanAndCreatedAtLessThan(userId, quarantineStatus.startTime, quarantineStatus.endTime);
        List<SingleAddLocationDataRequest> locationDataRequestList = new ArrayList<>();
        for(C19LocationLog log : locationLogList) {
            SingleAddLocationDataRequest data = new SingleAddLocationDataRequest();
            data.latitude = log.latitude;
            data.longitude = log.longitude;
            data.createdAt = log.createdAt;
            locationDataRequestList.add(data);
        }
        response.locationData = locationDataRequestList;
        return response;
    }
}
