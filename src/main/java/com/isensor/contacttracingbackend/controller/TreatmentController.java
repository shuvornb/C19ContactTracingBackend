package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.ReportSymptomsRequest;
import com.isensor.contacttracingbackend.communication.response.FetchAssessmentReportsResponse;
import com.isensor.contacttracingbackend.communication.response.FetchQuarantineStatusResponse;
import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.TreatmentService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing/treatment")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger log = LoggerFactory.getLogger(TreatmentController.class);

    @RequestMapping(value = "/self-reporting", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse updateConsentStatus(@RequestParam(name = "token", required = false) String token, @RequestBody ReportSymptomsRequest request) {
        log.info("Self Report API Invoked.");
        jwtUtils.validateToken(token);
        treatmentService.reportSymptoms(request);
        return new OKResponse("Symptoms reported successfully");
    }

    @RequestMapping(value = "/assessment-report/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FetchAssessmentReportsResponse fetchInformation(@RequestParam(name = "token", required = false) String token, @PathVariable(value = "userId") Long userId) {
        log.info("Fetch Assessment Reports API Invoked.");
        jwtUtils.validateToken(token);
        return treatmentService.fetchAssessmentReports(userId);
    }

    @RequestMapping(value = "/quarantine-status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FetchQuarantineStatusResponse fetchQuarantineStatus(@RequestParam(name = "token", required = false) String token, @RequestParam(name = "consentId", required = false) Long consentId) {
        log.info("Fetch Quarantine Status API Invoked.");
        jwtUtils.validateToken(token);
        log.info("Consent Id: {}", consentId);
        return treatmentService.fetchQuarantineStatus(consentId);
    }
}
