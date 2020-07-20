package com.isensor.contacttracingbackend.communication.request;

public class SurveyQuestion {
    public String question;
    public String answer;

    public SurveyQuestion() {
    }

    public SurveyQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "SurveyQuestion{" +
                "question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
