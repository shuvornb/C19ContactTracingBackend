package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.UpdateConsentRequest;
import com.isensor.contacttracingbackend.communication.response.FetchConsentResponse;
import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.ConsentService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing/consent")
public class ConsentController {

    @Autowired
    private ConsentService consentService;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger log = LoggerFactory.getLogger(ConsentController.class);

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FetchConsentResponse fetchConsent(@RequestParam(name = "token", required = false) String token, @PathVariable(name = "userId") Long userId) {
        log.info("Fetch Consent API Invoked.");
        jwtUtils.validateToken(token);
        return consentService.fetchConsent(userId);
    }

    @RequestMapping(value = "/status", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse updateConsentStatus(@RequestParam(name = "token", required = false) String token, @RequestBody UpdateConsentRequest request) {
        log.info("Update Consent API Invoked.");
        jwtUtils.validateToken(token);
        consentService.updateConsentStatus(request);
        return new OKResponse("Consent updated successfully");
    }
}
