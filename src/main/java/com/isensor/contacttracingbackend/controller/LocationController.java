package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.AddLocationDataRequest;
import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.LocationService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/isensor/c19-contact-tracing/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private JWTUtils jwtUtils;

    private final Logger log = LoggerFactory.getLogger(LocationController.class);

    @RequestMapping(value = "/gps-data", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse addLocationData(@RequestParam(name = "token", required = false) String token, @RequestBody AddLocationDataRequest request) {
        log.info("Add Location Data API Invoked.");
        jwtUtils.validateToken(token);
        locationService.addLocationData(request);
        return new OKResponse("Location data added successfully");
    }
}
