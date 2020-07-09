package com.isensor.contacttracingbackend.communication.web;

import com.isensor.contacttracingbackend.communication.request.BasicRestRequest;

public class WebLoginRequest extends BasicRestRequest {
    public String username;
    public String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WebLoginRequest() {
    }

    public WebLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public WebLoginRequest(String message, String username, String password) {
        super(message);
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "WebLoginRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
