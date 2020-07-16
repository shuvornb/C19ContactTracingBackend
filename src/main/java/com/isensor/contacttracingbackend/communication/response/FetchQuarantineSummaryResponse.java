package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.communication.request.Location;
import com.isensor.contacttracingbackend.communication.request.SingleAddLocationDataRequest;

import java.util.List;

public class FetchQuarantineSummaryResponse extends BasicRestResponse{
    public Long startTime;
    public Long endTime;
    public List<Location> homeLocationData;
    public List<SingleAddLocationDataRequest> locationData;

    public FetchQuarantineSummaryResponse() {
    }

    public FetchQuarantineSummaryResponse(Long startTime, Long endTime, List<Location> homeLocationData, List<SingleAddLocationDataRequest> locationData) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.homeLocationData = homeLocationData;
        this.locationData = locationData;
    }

    public FetchQuarantineSummaryResponse(String message, Long startTime, Long endTime, List<Location> homeLocationData, List<SingleAddLocationDataRequest> locationData) {
        super(message);
        this.startTime = startTime;
        this.endTime = endTime;
        this.homeLocationData = homeLocationData;
        this.locationData = locationData;
    }

    @Override
    public String toString() {
        return "FetchQuarantineSummaryResponse{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", homeLocationData=" + homeLocationData +
                ", locationData=" + locationData +
                ", message='" + message + '\'' +
                '}';
    }
}