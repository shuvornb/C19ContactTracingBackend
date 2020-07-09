package com.isensor.contacttracingbackend.communication.response;

import com.isensor.contacttracingbackend.db.entity.C19BluetoothLog;

import java.util.List;

public class FetchBluetoothDeviceResponse extends BasicRestResponse{
    public int deviceCount;
    public List<C19BluetoothLog> deviceList;

    public FetchBluetoothDeviceResponse() {
    }

    public FetchBluetoothDeviceResponse(String message, int deviceCount, List<C19BluetoothLog> deviceList) {
        super(message);
        this.deviceCount = deviceCount;
        this.deviceList = deviceList;
    }

    public FetchBluetoothDeviceResponse(int deviceCount, List<C19BluetoothLog> deviceList) {
        this.deviceCount = deviceCount;
        this.deviceList = deviceList;
    }

    @Override
    public String toString() {
        return "FetchBluetoothDeviceResponse{" +
                "deviceCount=" + deviceCount +
                ", deviceList=" + deviceList +
                ", message='" + message + '\'' +
                '}';
    }
}
