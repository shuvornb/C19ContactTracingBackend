package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.NewsUpdatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing")
public class NewsUpdatesController {

    @Autowired
    private NewsUpdatesService newsUpdatesService;

    private Logger log = LoggerFactory.getLogger(NewsUpdatesController.class);
    private static final int INFORMATION_DEFAULT_COUNT = 3;

    @RequestMapping(value = "/news-updates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse fetchInformation(@RequestParam(name = "count", required = false) Integer count) {
        log.info("Fetch News Updates API Invoked.");
        if(count == null) count = INFORMATION_DEFAULT_COUNT;
        return new OKResponse(newsUpdatesService.fetchInformation(count));
    }
}
