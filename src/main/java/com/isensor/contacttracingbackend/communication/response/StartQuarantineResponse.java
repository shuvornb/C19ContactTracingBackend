package com.isensor.contacttracingbackend.communication.response;

public class StartQuarantineResponse extends BasicRestResponse{
    public Integer quarantinePeriod;
    public Long userId;

    public StartQuarantineResponse() {
    }

    public StartQuarantineResponse(Integer quarantinePeriod, Long userId) {
        this.quarantinePeriod = quarantinePeriod;
        this.userId = userId;
    }

    public StartQuarantineResponse(String message, Integer quarantinePeriod, Long userId) {
        super(message);
        this.quarantinePeriod = quarantinePeriod;
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "StartQuarantineResponse{" +
                "quarantinePeriod=" + quarantinePeriod +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
