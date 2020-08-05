package com.isensor.contacttracingbackend.communication.request;

public class UpdatePasswordRequest extends BasicRestRequest {
    public Long userId;
    public String oldPassword;
    public String newPassword;

    public UpdatePasswordRequest() {
    }

    public UpdatePasswordRequest(Long userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public UpdatePasswordRequest(String message, Long userId, String oldPassword, String newPassword) {
        super(message);
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UpdatePasswordRequest{" +
                "userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}