package com.isensor.contacttracingbackend.communication.request;

public class UpdateNicknameRequest extends BasicRestRequest{
    public Long userId;
    public String nickname;
    public String deviceName;
    public String deviceAddress;

    public UpdateNicknameRequest() {
    }

    public UpdateNicknameRequest(Long userId, String nickname, String deviceName, String deviceAddress) {
        this.userId = userId;
        this.nickname = nickname;
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
    }

    public UpdateNicknameRequest(String message, Long userId, String nickname, String deviceName, String deviceAddress) {
        super(message);
        this.userId = userId;
        this.nickname = nickname;
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
    }

    @Override
    public String toString() {
        return "UpdateNicknameRequest{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
