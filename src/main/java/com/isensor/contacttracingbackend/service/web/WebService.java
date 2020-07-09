package com.isensor.contacttracingbackend.service.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isensor.contacttracingbackend.communication.request.Symptom;
import com.isensor.contacttracingbackend.communication.web.*;
import com.isensor.contacttracingbackend.db.entity.*;
import com.isensor.contacttracingbackend.db.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WebService {

    @Autowired
    private C19TestCenterRepository testCenterRepository;

    @Autowired
    private C19SymptomReportingRepository symptomReportingRepository;

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private C19TreatmentLogRepository treatmentLogRepository;

    @Autowired
    private C19ConsentRepository consentRepository;

    private final Logger log = LoggerFactory.getLogger(WebService.class);

    public Long loginTestCenter(WebLoginRequest request){
        log.info("Request Data: {}", request.toString());
        // check if the password matches
        C19TestCenter testCenter = testCenterRepository.findTopByUsernameAndPassword(request.username, request.password);
        if(testCenter == null) return null;
        else return testCenter.id;
    }

    public C19TestCenter getTestCenterDetails(Long testCenterId) {
        return testCenterRepository.findTopById(testCenterId);
    }

    public List<SelfReportInfo> getSelfReportList(Long testCenterId) {
        // check if testCenterId exists or not
        List<C19SymptomReporting> reportList = symptomReportingRepository.findByTestCenterId(testCenterId);
        List<SelfReportInfo> selfReportInfoList = new ArrayList<>();
        Long serialNo = 1L;
        for(C19SymptomReporting report : reportList) {
            C19User user = userRepository.findTopById(report.userId);
            SelfReportInfo reportInfo = new SelfReportInfo();
            reportInfo.serialNo = serialNo++;
            reportInfo.name = user.getFullName();
            reportInfo.phoneNumber = user.phoneNumber;
            reportInfo.reportingTime = convertUnixTimestampToSimpleDateFormat(report.reportingTime);
            reportInfo.reportURL = "/details/" + report.id;
            selfReportInfoList.add(reportInfo);
        }
        return selfReportInfoList;
    }

    private String convertUnixTimestampToSimpleDateFormat(Long timestamp) {
        Date date = new java.util.Date(timestamp);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-4"));
        return sdf.format(date);
    }

    public C19SymptomReporting getASingleReport(Long reportId) {
        return symptomReportingRepository.findTopById(reportId);
    }

    public PatientInfo getPatientInfo(Long userId) {
        C19User user = userRepository.findTopById(userId);
        PatientInfo patientInfo = new PatientInfo();
        patientInfo.patientId = userId;
        patientInfo.name = user.getFullName();
        patientInfo.address = user.getFullAddress();
        patientInfo.gender = user.gender;
        patientInfo.phoneNumber = user.phoneNumber;
        patientInfo.age = user.calculateAgeFromDOB();
        return patientInfo;
    }

    public List<Symptom> convertStringToList(String symptoms) {
        ObjectMapper mapper = new ObjectMapper();
        List<Symptom> symptomList = new ArrayList<>();
        try {
             symptomList = mapper.readValue(symptoms, new TypeReference<List<Symptom>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        int serialNo = 1;
        for (Symptom symptom : symptomList) {
            symptom.serialNo = serialNo++;
        }
        return symptomList;
    }

    public void initiateTest(Long testCenterId, Long userId, Long reportId) {
        C19TreatmentLog treatmentLog = new C19TreatmentLog();
        treatmentLog.testCenterId = testCenterId;
        treatmentLog.userId = userId;
        treatmentLog.reportId = reportId;
        treatmentLog.consentIds = createConsent(userId, testCenterId);
        treatmentLog.tracingStatus = "OFF";
        treatmentLog.testResult = "PENDING";
        treatmentLogRepository.save(treatmentLog);
    }

    private String createConsent(Long userId, Long testCenterId) {
        C19Consent firstConsent = new C19Consent();
        firstConsent.userId = userId;
        firstConsent.testCenterId = testCenterId;
        firstConsent.isActive = true;
        firstConsent.userResponse = "PENDING";
        firstConsent.createdAt = System.currentTimeMillis();
        firstConsent.title = "Consent to information release";
        firstConsent.details = "By clicking on the [I agree] button, I authorize the Test Center to view/share my reported information with regards to demographics and symptoms. The Test Center will make further assessment and arrangement for further testing.";
        firstConsent = consentRepository.save(firstConsent);

        C19Consent secondConsent = new C19Consent();
        secondConsent.userId = userId;
        secondConsent.testCenterId = testCenterId;
        secondConsent.isActive = true;
        secondConsent.userResponse = "PENDING";
        secondConsent.createdAt = System.currentTimeMillis();
        secondConsent.title = "Consent to location tracing";
        secondConsent.details = "By clicking on the [I agree] button, I authorize the Test Center to trace the location of my phone until the test results show negative. I acknowledge that the tracing will be deactivated once the test results show negative. I authorize the Test Center to trace my location once the test results shows positive.";
        secondConsent = consentRepository.save(secondConsent);

        return firstConsent.id + " : " + secondConsent.id;
    }

    public List<ActivePatientInfo> getActivePatientList(Long testCenterId) {
        List<ActivePatientInfo> patientInfos = new ArrayList<>();
        List<C19TreatmentLog> treatmentLogs = treatmentLogRepository.findByTestCenterId(testCenterId);
        if(!treatmentLogs.isEmpty()){
            Long serialNo = 1L;
            for(C19TreatmentLog treatmentLog : treatmentLogs) {
                C19User user = userRepository.findTopById(treatmentLog.userId);
                ActivePatientInfo patientInfo = new ActivePatientInfo();
                patientInfo.serialNo = serialNo++;
                patientInfo.name = user.getFullName();
                patientInfo.phoneNumber = user.phoneNumber;
                patientInfo.patientId = user.id;
                patientInfo.activationTime = treatmentLog.activationTime == null ? "N/A" : convertUnixTimestampToSimpleDateFormat(treatmentLog.activationTime);
                patientInfo.deactivationTime = treatmentLog.deactivationTime == null ? "N/A" : convertUnixTimestampToSimpleDateFormat(treatmentLog.deactivationTime);
                patientInfo.testType = treatmentLog.testType;
                patientInfo.testResult = treatmentLog.testResult;
                patientInfo.tracingStatus = treatmentLog.tracingStatus;
                patientInfo.testResultURL = "/test-results/" + treatmentLog.id;
                patientInfos.add(patientInfo);
            }
        }
        return patientInfos;
    }

    public C19TreatmentLog updateTestResults(UpdateTestResultsRequest request) {
        C19TreatmentLog treatmentLog = treatmentLogRepository.findTopById(request.treatmentLogId);
        treatmentLog.testResult = request.testResult;
        if(request.testResult.equals("NEGATIVE")) {
            treatmentLog.tracingStatus = "OFF";
            treatmentLog.deactivationTime = System.currentTimeMillis();
        }
        return treatmentLogRepository.save(treatmentLog);
    }
}
