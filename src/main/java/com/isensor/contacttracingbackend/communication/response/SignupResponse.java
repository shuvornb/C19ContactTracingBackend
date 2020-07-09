package com.isensor.contacttracingbackend.communication.response;

public class SignupResponse extends BasicRestResponse{
    public Long userId;

    public SignupResponse() {
    }

    public SignupResponse(Long userId) {
        this.userId = userId;
    }

    public SignupResponse(String message, Long userId) {
        super(message);
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SignupResponse{" +
                "userId=" + userId +
                ", message='" + message + '\'' +
                '}';
    }
}
