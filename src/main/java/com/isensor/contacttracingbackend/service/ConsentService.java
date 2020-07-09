package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.request.UpdateConsentRequest;
import com.isensor.contacttracingbackend.communication.response.FetchConsentResponse;
import com.isensor.contacttracingbackend.db.entity.C19Consent;
import com.isensor.contacttracingbackend.db.entity.C19TreatmentLog;
import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.db.repository.C19ConsentRepository;
import com.isensor.contacttracingbackend.db.repository.C19TreatmentLogRepository;
import com.isensor.contacttracingbackend.db.repository.C19UserRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import com.isensor.contacttracingbackend.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsentService {

    @Autowired
    private C19ConsentRepository consentRepository;

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private C19TreatmentLogRepository treatmentLogRepository;

    private Logger log = LoggerFactory.getLogger(ConsentService.class);

    public FetchConsentResponse fetchConsent(Long userId) {
        // check if a valid user
        C19User user = userRepository.findTopById(userId);
        log.info("Found user: {}", user);
        if(user == null) {
            log.info("User with the given id does not exist");
            throw new NotFoundException("User with the given id does not exist");
        }
        // fetch consent/s from consent table
        List<C19Consent> consentList = consentRepository.findByUserId(userId);
        // respond
        FetchConsentResponse response = new FetchConsentResponse();
        response.consentCount = consentList.size();
        response.consentList = consentList;
        return response;
    }

    public void updateConsentStatus(UpdateConsentRequest request) {
        // fetch the consent
        C19Consent consent = consentRepository.findTopById(request.consentId);

        // check validity
        if(consent == null) {
            log.error("Consent not found");
            throw new NotFoundException("Consent not found");
        }

        // check activeness
        if(!consent.isActive) {
            log.error("Consent is no more active");
            throw new BadRequestException("Consent is no more active");
        }

        // check existing consent status
        if(consent.userResponse.equals("ACCEPTED") || consent.userResponse.equals("REJECTED")) {
            log.error("Consent can not be updated");
            throw new BadRequestException("Consent can not be updated");
        }

        // check new consent status
        if(!(request.userResponse.equals("ACCEPTED") || request.userResponse.equals("REJECTED"))) {
            log.error("Consent can not be updated");
            throw new BadRequestException("Consent can not be updated");
        }

        consent.userResponse = request.userResponse;
        consent.userResponseTime = request.userResponseTime;
        consentRepository.save(consent);

        if(request.userResponse.equals("ACCEPTED") && consent.title.equals("Consent to location tracing")) {
            C19TreatmentLog treatmentLog = treatmentLogRepository.findTopByConsentIdsContaining(String.valueOf(consent.id));
            treatmentLog.activationTime = System.currentTimeMillis();
            treatmentLog.tracingStatus = "ON";
            treatmentLogRepository.save(treatmentLog);
        }
        log.info("Consent updated successfully");
    }
}
