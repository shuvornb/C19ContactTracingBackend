package com.isensor.contacttracingbackend.communication.request;

public class StartQuarantineRequest extends BasicRestRequest{
    public Long userId;
    public Long startTime;

    public StartQuarantineRequest() {
    }

    public StartQuarantineRequest(Long userId, Long startTime) {
        this.userId = userId;
        this.startTime = startTime;
    }

    public StartQuarantineRequest(String message, Long userId, Long startTime) {
        super(message);
        this.userId = userId;
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "StartQuarantineRequest{" +
                "userId=" + userId +
                ", startTime=" + startTime +
                ", message='" + message + '\'' +
                '}';
    }
}
