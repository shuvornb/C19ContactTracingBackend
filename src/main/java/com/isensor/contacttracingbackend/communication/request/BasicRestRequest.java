package com.isensor.contacttracingbackend.communication.request;

import java.io.Serializable;

public class BasicRestRequest implements Serializable{
    public String message;

    public BasicRestRequest(String message) {
        this.message = message;
    }

    public BasicRestRequest() {

    }

    @Override
    public String toString() {
        return "BasicRestRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}
