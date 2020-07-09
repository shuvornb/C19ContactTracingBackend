package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.db.entity.C19TestCenter;

import java.util.List;

public class FetchAllTestCentersResponse extends BasicRestResponse{
    public int testCenterCount;
    public List<C19TestCenter> testCenterList;

    public FetchAllTestCentersResponse() {
    }

    public FetchAllTestCentersResponse(String message, int testCenterCount, List<C19TestCenter> testCenterList) {
        super(message);
        this.testCenterCount = testCenterCount;
        this.testCenterList = testCenterList;
    }

    public FetchAllTestCentersResponse(String message, List<C19TestCenter> testCenterList) {
        super(message);
        this.testCenterList = testCenterList;
    }

    @Override
    public String toString() {
        return "FetchAllTestCentersResponse{" +
                "testCenterCount=" + testCenterCount +
                ", testCenterList=" + testCenterList +
                ", message='" + message + '\'' +
                '}';
    }
}
