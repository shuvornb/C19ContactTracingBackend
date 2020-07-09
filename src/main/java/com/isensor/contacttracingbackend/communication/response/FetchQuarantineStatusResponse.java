package com.isensor.contacttracingbackend.communication.response;

public class FetchQuarantineStatusResponse extends BasicRestResponse{
    public String testCenterName;
    public String testCenterPhoneNumber;
    public String trackingStatus;
    public String testResult;
    public Long activationTime;
    public Long deactivationTime;

    public FetchQuarantineStatusResponse() {
    }

    public FetchQuarantineStatusResponse(String testCenterName, String testCenterPhoneNumber, String trackingStatus, String testResult, Long activationTime, Long deactivationTime) {
        this.testCenterName = testCenterName;
        this.testCenterPhoneNumber = testCenterPhoneNumber;
        this.trackingStatus = trackingStatus;
        this.testResult = testResult;
        this.activationTime = activationTime;
        this.deactivationTime = deactivationTime;
    }

    public FetchQuarantineStatusResponse(String message, String testCenterName, String testCenterPhoneNumber, String trackingStatus, String testResult, Long activationTime, Long deactivationTime) {
        super(message);
        this.testCenterName = testCenterName;
        this.testCenterPhoneNumber = testCenterPhoneNumber;
        this.trackingStatus = trackingStatus;
        this.testResult = testResult;
        this.activationTime = activationTime;
        this.deactivationTime = deactivationTime;
    }

    @Override
    public String toString() {
        return "FetchQuarantineStatusResponse{" +
                "testCenterName='" + testCenterName + '\'' +
                ", testCenterPhoneNumber='" + testCenterPhoneNumber + '\'' +
                ", trackingStatus='" + trackingStatus + '\'' +
                ", testResult='" + testResult + '\'' +
                ", activationTime=" + activationTime +
                ", deactivationTime=" + deactivationTime +
                ", message='" + message + '\'' +
                '}';
    }
}
