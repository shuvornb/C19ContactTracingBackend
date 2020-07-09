package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.response.FetchAllTestCentersResponse;
import com.isensor.contacttracingbackend.db.entity.C19TestCenter;
import com.isensor.contacttracingbackend.db.repository.C19TestCenterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCenterService {

    @Autowired
    private C19TestCenterRepository c19TestCenterRepository;

    private Logger log = LoggerFactory.getLogger(TestCenterService.class);

    public FetchAllTestCentersResponse fetchAllTestCenters() {
        List<C19TestCenter> testCenterList = c19TestCenterRepository.findAll();
        FetchAllTestCentersResponse response = new FetchAllTestCentersResponse();
        response.message = "Fetching test centers successful";
        response.testCenterCount = testCenterList.size();
        response.testCenterList = testCenterList;
        return response;
    }
}
