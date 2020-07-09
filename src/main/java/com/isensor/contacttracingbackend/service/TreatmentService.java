package com.isensor.contacttracingbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isensor.contacttracingbackend.communication.request.ReportSymptomsRequest;
import com.isensor.contacttracingbackend.communication.response.FetchAssessmentReportsResponse;
import com.isensor.contacttracingbackend.communication.response.FetchQuarantineStatusResponse;
import com.isensor.contacttracingbackend.db.entity.C19SymptomReporting;
import com.isensor.contacttracingbackend.db.entity.C19TestCenter;
import com.isensor.contacttracingbackend.db.entity.C19TreatmentLog;
import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.db.repository.C19SymptomReportingRepository;
import com.isensor.contacttracingbackend.db.repository.C19TestCenterRepository;
import com.isensor.contacttracingbackend.db.repository.C19TreatmentLogRepository;
import com.isensor.contacttracingbackend.db.repository.C19UserRepository;
import com.isensor.contacttracingbackend.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatmentService {

    @Autowired
    private C19SymptomReportingRepository symptomReportingRepository;

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private C19TestCenterRepository testCenterRepository;

    @Autowired
    private C19TreatmentLogRepository treatmentLogRepository;

    private Logger log = LoggerFactory.getLogger(TreatmentService.class);

    public void reportSymptoms(ReportSymptomsRequest request) {
        // check if the user exists
        C19User user = userRepository.findTopById(request.userId);
        if(user == null) {
            log.error("User not found");
            throw new NotFoundException("User not found");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String symptomJSONString = null;
        try {
            symptomJSONString = objectMapper.writeValueAsString(request.symptomList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        log.info("JSON String for Symptoms: {}", symptomJSONString);

        C19SymptomReporting report = new C19SymptomReporting();
        report.userId = request.userId;
        report.testCenterId = request.testCenterId;
        report.symptoms = symptomJSONString;
        report.reportingTime = request.reportingTime;
        report.notes = request.notes;
        symptomReportingRepository.save(report);
        log.info("Symptoms reported successfully");
    }

    public FetchAssessmentReportsResponse fetchAssessmentReports(Long userId) {
        List<C19SymptomReporting> reportList = symptomReportingRepository.findByUserIdOrderByReportingTimeDesc(userId);
        FetchAssessmentReportsResponse response = new FetchAssessmentReportsResponse();
        response.message = "Fetching assessment reports successful";
        response.assessmentReportCount = reportList.size();
        response.assessmentReportList = reportList;
        return response;

    }

    public FetchQuarantineStatusResponse fetchQuarantineStatus(Long consentId) {
        //C19TreatmentLog treatmentLog = treatmentLogRepository.getQuarantineStatusByConsent(consentId);
        C19TreatmentLog treatmentLog = treatmentLogRepository.findTopByConsentIdsContaining(String.valueOf(consentId));
        log.info("Treatment Log: {}", treatmentLog.toString());
        FetchQuarantineStatusResponse response = new FetchQuarantineStatusResponse();
        C19TestCenter testCenter = testCenterRepository.findTopById(treatmentLog.testCenterId);
        response.testCenterName = testCenter.title;
        response.testCenterPhoneNumber = testCenter.phone;
        response.activationTime = treatmentLog.activationTime;
        response.deactivationTime = treatmentLog.deactivationTime;
        response.trackingStatus = treatmentLog.tracingStatus;
        response.testResult = treatmentLog.testResult;

        return response;
    }
}
