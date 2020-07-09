package com.isensor.contacttracingbackend.communication.request;

import java.util.List;

public class UpdateHomeLocationRequest extends BasicRestRequest{
    public Long userId;
    public List<Location> homeLocationData;

    public UpdateHomeLocationRequest() {
    }

    public UpdateHomeLocationRequest(Long userId, List<Location> homeLocationData) {
        this.userId = userId;
        this.homeLocationData = homeLocationData;
    }

    public UpdateHomeLocationRequest(String message, Long userId, List<Location> homeLocationData) {
        super(message);
        this.userId = userId;
        this.homeLocationData = homeLocationData;
    }

    @Override
    public String toString() {
        return "UpdateHomeLocationRequest{" +
                "userId=" + userId +
                ", homeLocationData=" + homeLocationData +
                ", message='" + message + '\'' +
                '}';
    }
}