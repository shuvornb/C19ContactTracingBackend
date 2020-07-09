package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.TestCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing")
public class TestCenterController {

    @Autowired
    private TestCenterService testCenterService;

    private Logger log = LoggerFactory.getLogger(TestCenterController.class);

    @RequestMapping(value = "/test-center", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse fetchAllTestCenters() {
        log.info("Fetch Test Centers API Invoked.");
        return new OKResponse(testCenterService.fetchAllTestCenters());
    }
}
