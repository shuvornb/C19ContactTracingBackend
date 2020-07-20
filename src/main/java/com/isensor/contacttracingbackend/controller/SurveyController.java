package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.AddNewSurveyRequest;
import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.SurveyService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing/survey")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger log = LoggerFactory.getLogger(SurveyController.class);

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse addSurvey(@RequestParam(name = "token", required = false) String token, @RequestBody AddNewSurveyRequest request) {
        log.info("Add Survey API Invoked.");
        jwtUtils.validateToken(token);
        surveyService.addNewSurvey(request);
        return new OKResponse("Survey added successfully");
    }
}
