package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.request.StartQuarantineRequest;
import com.isensor.contacttracingbackend.db.entity.C19QuarantineStatus;
import com.isensor.contacttracingbackend.db.repository.C19QuarantineStatusRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuarantineService {

    @Autowired
    private C19QuarantineStatusRepository quarantineStatusRepository;

    private final Logger log = LoggerFactory.getLogger(QuarantineService.class);

    public void startQuarantine(StartQuarantineRequest request) {
        // check if this person already in a quarantine or not
        List<C19QuarantineStatus> alreadyActiveQuarantines = quarantineStatusRepository.findByUserIdAndIsActive(request.userId, true);
        if(!alreadyActiveQuarantines.isEmpty()) {
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
}
