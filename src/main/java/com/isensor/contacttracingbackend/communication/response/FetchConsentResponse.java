package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.db.entity.C19Consent;

import java.util.List;

public class FetchConsentResponse extends BasicRestResponse{
    public int consentCount;
    public List<C19Consent> consentList;

    public FetchConsentResponse() {
    }

    public FetchConsentResponse(String message, int consentCount, List<C19Consent> consentList) {
        super(message);
        this.consentCount = consentCount;
        this.consentList = consentList;
    }

    public FetchConsentResponse(int consentCount, List<C19Consent> consentList) {
        this.consentCount = consentCount;
        this.consentList = consentList;
    }

    @Override
    public String toString() {
        return "FetchConsentResponse{" +
                "consentCount=" + consentCount +
                ", consentList=" + consentList +
                ", message='" + message + '\'' +
                '}';
    }
}
