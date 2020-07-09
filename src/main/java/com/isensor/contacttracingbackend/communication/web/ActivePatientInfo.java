package com.isensor.contacttracingbackend.communication.web;

public class ActivePatientInfo {
    public Long serialNo;
    public Long patientId;
    public String name;
    public String phoneNumber;
    public String activationTime;
    public String deactivationTime;
    public String testType;
    public String testResult;
    public String tracingStatus;
    public String testResultURL;

    public ActivePatientInfo() {
    }

    public ActivePatientInfo(Long serialNo, Long patientId, String name, String phoneNumber, String activationTime, String deactivationTime, String testType, String testResult, String tracingStatus, String testResultURL) {
        this.serialNo = serialNo;
        this.patientId = patientId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.activationTime = activationTime;
        this.deactivationTime = deactivationTime;
        this.testType = testType;
        this.testResult = testResult;
        this.tracingStatus = tracingStatus;
        this.testResultURL = testResultURL;
    }

    @Override
    public String toString() {
        return "ActivePatientInfo{" +
                "serialNo=" + serialNo +
                ", patientId=" + patientId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", activationTime='" + activationTime + '\'' +
                ", deactivationTime='" + deactivationTime + '\'' +
                ", testType='" + testType + '\'' +
                ", testResult='" + testResult + '\'' +
                ", tracingStatus='" + tracingStatus + '\'' +
                ", testResultURL='" + testResultURL + '\'' +
                '}';
    }
}
