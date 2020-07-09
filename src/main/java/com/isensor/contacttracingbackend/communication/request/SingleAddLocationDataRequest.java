package com.isensor.contacttracingbackend.communication.request;

public class SingleAddLocationDataRequest {
    public Long createdAt;
    public Double latitude;
    public Double longitude;

    public SingleAddLocationDataRequest() {
    }

    public SingleAddLocationDataRequest(Long createdAt, Double latitude, Double longitude) {
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "SingleAddLocationDataRequest{" +
                "createdAt=" + createdAt +
                ", Latitude=" + latitude +
                ", Longitude=" + longitude +
                '}';
    }
}
