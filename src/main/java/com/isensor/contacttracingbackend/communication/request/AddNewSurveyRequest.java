package com.isensor.contacttracingbackend.communication.request;

import java.util.List;

public class AddNewSurveyRequest extends BasicRestRequest{
    public Long userId;
    public Long surveyCompletionTime;
    public List<SurveyQuestion> questionList;

    public AddNewSurveyRequest() {
    }

    public AddNewSurveyRequest(Long userId, Long surveyCompletionTime, List<SurveyQuestion> questionList) {
        this.userId = userId;
        this.surveyCompletionTime = surveyCompletionTime;
        this.questionList = questionList;
    }

    public AddNewSurveyRequest(String message, Long userId, Long surveyCompletionTime, List<SurveyQuestion> questionList) {
        super(message);
        this.userId = userId;
        this.surveyCompletionTime = surveyCompletionTime;
        this.questionList = questionList;
    }

    @Override
    public String toString() {
        return "AddNewSurveyRequest{" +
                "userId=" + userId +
                ", surveyCompletionTime=" + surveyCompletionTime +
                ", questionList=" + questionList +
                ", message='" + message + '\'' +
                '}';
    }
}
