package com.isensor.contacttracingbackend.communication.request;

import java.util.List;

public class AddLocationDataRequest extends BasicRestRequest{
    public Long userId;
    public List<SingleAddLocationDataRequest> locationData;

    public AddLocationDataRequest() {
    }

    public AddLocationDataRequest(Long userId, List<SingleAddLocationDataRequest> locationData) {
        this.userId = userId;
        this.locationData = locationData;
    }

    public AddLocationDataRequest(String message, Long userId, List<SingleAddLocationDataRequest> locationData) {
        super(message);
        this.userId = userId;
        this.locationData = locationData;
    }

    @Override
    public String toString() {
        return "AddLocationRequest{" +
                "userId=" + userId +
                ", locationData=" + locationData +
                ", message='" + message + '\'' +
                '}';
    }
}
