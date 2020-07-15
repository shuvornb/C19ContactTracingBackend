package com.isensor.contacttracingbackend.communication.request;

public class Location {
    public String address;
    public Double latitude;
    public Double longitude;

    public Location() {
    }

    public Location(String address, Double latitude, Double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
