package com.isensor.contacttracingbackend.communication.request;

import java.util.List;

public class ReportSymptomsRequest extends BasicRestRequest{
    public Long userId;
    public Long testCenterId;
    public Long reportingTime;
    public List<Symptom> symptomList;
    public String notes;

    public ReportSymptomsRequest() {
    }

    public ReportSymptomsRequest(Long userId, Long testCenterId, Long reportingTime, List<Symptom> symptomList, String notes) {
        this.userId = userId;
        this.testCenterId = testCenterId;
        this.reportingTime = reportingTime;
        this.symptomList = symptomList;
        this.notes = notes;
    }

    public ReportSymptomsRequest(String message, Long userId, Long testCenterId, Long reportingTime, List<Symptom> symptomList, String notes) {
        super(message);
        this.userId = userId;
        this.testCenterId = testCenterId;
        this.reportingTime = reportingTime;
        this.symptomList = symptomList;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ReportSymptomsRequest{" +
                "userId=" + userId +
                ", testCenterId=" + testCenterId +
                ", reportingTime=" + reportingTime +
                ", symptomList=" + symptomList +
                ", notes='" + notes + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
