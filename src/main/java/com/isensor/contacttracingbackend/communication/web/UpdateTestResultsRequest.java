package com.isensor.contacttracingbackend.communication.web;

import com.isensor.contacttracingbackend.communication.request.BasicRestRequest;

public class UpdateTestResultsRequest extends BasicRestRequest {
    public Long treatmentLogId;
    public String testResult;

    public Long getTreatmentLogId() {
        return treatmentLogId;
    }

    public void setTreatmentLogId(Long treatmentLogId) {
        this.treatmentLogId = treatmentLogId;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }

    public UpdateTestResultsRequest() {
    }

    public UpdateTestResultsRequest(Long treatmentLogId, String testResult) {
        this.treatmentLogId = treatmentLogId;
        this.testResult = testResult;
    }

    public UpdateTestResultsRequest(String message, Long treatmentLogId, String testResult) {
        super(message);
        this.treatmentLogId = treatmentLogId;
        this.testResult = testResult;
    }

    @Override
    public String toString() {
        return "UpdateTestResultsRequest{" +
                "treatmentLogId=" + treatmentLogId +
                ", testResult='" + testResult + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
