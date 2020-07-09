package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.db.entity.C19NewsUpdates;

import java.util.List;

public class FetchNewsUpdatesResponse extends BasicRestResponse{
    public int newsCount;
    public List<C19NewsUpdates> newsList;

    public FetchNewsUpdatesResponse() {
    }

    public FetchNewsUpdatesResponse(String message, int informationCount, List<C19NewsUpdates> informationList) {
        super(message);
        this.newsCount = informationCount;
        this.newsList = informationList;
    }

    public FetchNewsUpdatesResponse(int informationCount, List<C19NewsUpdates> informationList) {
        this.newsCount = informationCount;
        this.newsList = informationList;
    }

    @Override
    public String toString() {
        return "FetchNewsUpdatesResponse{" +
                "newsCount=" + newsCount +
                ", newsList=" + newsList +
                ", message='" + message + '\'' +
                '}';
    }
}
