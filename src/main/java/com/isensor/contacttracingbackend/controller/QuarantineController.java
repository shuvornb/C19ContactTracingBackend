package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.StartQuarantineRequest;
import com.isensor.contacttracingbackend.communication.request.UpdateHomeLocationRequest;
import com.isensor.contacttracingbackend.communication.response.FetchQuarantineSummaryResponse;
import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.QuarantineService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing/quarantine")
public class QuarantineController {

    @Autowired
    private QuarantineService quarantineService;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger log = LoggerFactory.getLogger(QuarantineController.class);

    @RequestMapping(value = "/start", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse startQuarantine(@RequestParam(name = "token", required = false) String token, @RequestBody StartQuarantineRequest request) {
        log.info("Start Quarantine API Invoked.");
        jwtUtils.validateToken(token);
        quarantineService.startQuarantine(request);
        return new OKResponse("Quarantine started successfully");
    }

    @RequestMapping(value = "/home-location", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse updateHomeLocation(@RequestParam(name = "token", required = false) String token, @RequestBody UpdateHomeLocationRequest request) {
        log.info("Update Home Location API Invoked.");
        jwtUtils.validateToken(token);
        quarantineService.updateHomeLocation(request);
        return new OKResponse("Home location updated successfully");
    }

    @RequestMapping(value = "/summary/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FetchQuarantineSummaryResponse fetchQuarantineSummary(@RequestParam(name = "token", required = false) String token, @PathVariable Long userId) {
        log.info("Fetch Quarantine Summary API Invoked.");
        jwtUtils.validateToken(token);
        return quarantineService.fetchQuarantineSummary(userId);
    }
}
