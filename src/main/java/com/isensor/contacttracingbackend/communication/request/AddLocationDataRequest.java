package com.isensor.contacttracingbackend.communication.request;

import java.util.List;

public class AddLocationDataRequest extends BasicRestRequest{
    public Long userId;
    public Long endTime;
    public List<SingleAddLocationDataRequest> locationData;

    public AddLocationDataRequest() {
    }

    public AddLocationDataRequest(Long userId, Long endTime, List<SingleAddLocationDataRequest> locationData) {
        this.userId = userId;
        this.endTime = endTime;
        this.locationData = locationData;
    }

    public AddLocationDataRequest(String message, Long userId, Long endTime, List<SingleAddLocationDataRequest> locationData) {
        super(message);
        this.userId = userId;
        this.endTime = endTime;
        this.locationData = locationData;
    }

    @Override
    public String toString() {
        return "AddLocationDataRequest{" +
                "userId=" + userId +
                ", endTime=" + endTime +
                ", locationData=" + locationData +
                ", message='" + message + '\'' +
                '}';
    }
}
