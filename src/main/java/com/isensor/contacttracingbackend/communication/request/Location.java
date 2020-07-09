package com.isensor.contacttracingbackend.communication.request;

public class Location {
    public Double latitude;
    public Double longitude;

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
