package com.isensor.contacttracingbackend.controller.web;

import com.isensor.contacttracingbackend.communication.request.StartQuarantineRequest;
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
        log.info("Self Report API Invoked.");
        jwtUtils.validateToken(token);
        quarantineService.startQuarantine(request);
        return new OKResponse("Quarantine started successfully");
    }
}
