package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.db.entity.C19User;

public class LoginResponse extends BasicRestResponse{
    public String token;
    public Long userId;
    public C19User userInfo;

    public LoginResponse() {
    }

    public LoginResponse(String token, Long userId, C19User userInfo) {
        this.token = token;
        this.userId = userId;
        this.userInfo = userInfo;
    }

    public LoginResponse(String message, String token, Long userId, C19User userInfo) {
        super(message);
        this.token = token;
        this.userId = userId;
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                ", userInfo=" + userInfo +
                ", message='" + message + '\'' +
                '}';
    }
}
