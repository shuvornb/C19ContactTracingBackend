package com.isensor.contacttracingbackend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isensor.contacttracingbackend.communication.request.AddNewSurveyRequest;
import com.isensor.contacttracingbackend.db.entity.C19Survey;
import com.isensor.contacttracingbackend.db.repository.C19SurveyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyService {

    @Autowired
    private C19SurveyRepository surveyRepository;

    private final Logger log = LoggerFactory.getLogger(SurveyService.class);

    public void addNewSurvey(AddNewSurveyRequest request) {
        C19Survey survey = new C19Survey();
        survey.userId = request.userId;
        survey.surveyCompletionTime = request.surveyCompletionTime;
        ObjectMapper objectMapper = new ObjectMapper();
        String surveyResponseJSONString = null;
        try {
            surveyResponseJSONString = objectMapper.writeValueAsString(request.questionList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        log.info("JSON String for Symptoms: {}", surveyResponseJSONString);
        survey.response = surveyResponseJSONString;
        surveyRepository.save(survey);
        log.info("Survey added successfully");
    }
}
