package com.isensor.contacttracingbackend.communication.response;

public class PhoneVerificationResponse extends BasicRestResponse{
    public Long id;

    public PhoneVerificationResponse() {
    }

    public PhoneVerificationResponse(Long id) {
        this.id = id;
    }

    public PhoneVerificationResponse(String message, Long id) {
        super(message);
        this.id = id;
    }

    @Override
    public String toString() {
        return "PhoneVerificationResponse{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
