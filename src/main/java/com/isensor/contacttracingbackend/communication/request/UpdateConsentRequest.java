package com.isensor.contacttracingbackend.communication.request;

public class UpdateConsentRequest extends BasicRestRequest {
    public Long consentId;
    public String userResponse;
    public Long userResponseTime;

    public UpdateConsentRequest() {
    }

    public UpdateConsentRequest(Long consentId, String userResponse, Long userResponseTime) {
        this.consentId = consentId;
        this.userResponse = userResponse;
        this.userResponseTime = userResponseTime;
    }

    public UpdateConsentRequest(String message, Long consentId, String userResponse, Long userResponseTime) {
        super(message);
        this.consentId = consentId;
        this.userResponse = userResponse;
        this.userResponseTime = userResponseTime;
    }

    @Override
    public String toString() {
        return "UpdateConsentRequest{" +
                "consentId=" + consentId +
                ", userResponse='" + userResponse + '\'' +
                ", userResponseTime=" + userResponseTime +
                ", message='" + message + '\'' +
                '}';
    }
}
