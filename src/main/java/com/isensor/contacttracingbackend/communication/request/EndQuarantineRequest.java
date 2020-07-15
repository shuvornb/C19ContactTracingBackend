package com.isensor.contacttracingbackend.communication.request;

public class EndQuarantineRequest extends BasicRestRequest{
    public Long userId;
    public Long endTime;

    public EndQuarantineRequest() {
    }

    public EndQuarantineRequest(String message, Long userId, Long endTime) {
        super(message);
        this.userId = userId;
        this.endTime = endTime;
    }

    public EndQuarantineRequest(Long userId, Long endTime) {
        this.userId = userId;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "EndQuarantineRequest{" +
                "userId=" + userId +
                ", endTime=" + endTime +
                ", message='" + message + '\'' +
                '}';
    }
}