package com.isensor.contacttracingbackend.communication.request;

public class VerifyPhoneNumberRequest extends BasicRestRequest {
    public Long id;
    public String phoneNumber;
    public Integer verificationCode;

    public VerifyPhoneNumberRequest() {
    }

    public VerifyPhoneNumberRequest(Long id, String phoneNumber, Integer verificationCode) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }

    public VerifyPhoneNumberRequest(String message, Long id, String phoneNumber, Integer verificationCode) {
        super(message);
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "VerifyPhoneNumberRequest{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", verificationCode=" + verificationCode +
                ", message='" + message + '\'' +
                '}';
    }
}
