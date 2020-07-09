package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.response.FetchNewsUpdatesResponse;
import com.isensor.contacttracingbackend.db.entity.C19NewsUpdates;
import com.isensor.contacttracingbackend.db.repository.C19NewsUpdatesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsUpdatesService {

    @Autowired
    private C19NewsUpdatesRepository c19InformationRepository;

    private Logger log = LoggerFactory.getLogger(NewsUpdatesService.class);

    public FetchNewsUpdatesResponse fetchInformation(int count) {
        List<C19NewsUpdates> infoList = c19InformationRepository.getLatestNewsUpdates(count);
        FetchNewsUpdatesResponse response = new FetchNewsUpdatesResponse();
        response.message = "Fetching news updates successful";
        response.newsCount = infoList.size();
        response.newsList = infoList;
        return response;
    }
}
