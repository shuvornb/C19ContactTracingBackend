package com.isensor.contacttracingbackend.communication.response;

public class FetchProfileInfoResponse extends BasicRestResponse{
    public Long userId;
    public String email;
    public String phoneNumber;
    public String gender;
    public String dateOfBirth;
    public String fullName;
    public String fullAddress;


    public FetchProfileInfoResponse() {
    }

    public FetchProfileInfoResponse(Long userId, String email, String phoneNumber, String gender, String dateOfBirth, String fullName, String fullAddress) {
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.fullAddress = fullAddress;
    }

    public FetchProfileInfoResponse(String message, Long userId, String email, String phoneNumber, String gender, String dateOfBirth, String fullName, String fullAddress) {
        super(message);
        this.userId = userId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "FetchProfileInfoResponse{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", fullName='" + fullName + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
