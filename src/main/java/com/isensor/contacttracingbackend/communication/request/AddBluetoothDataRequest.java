package com.isensor.contacttracingbackend.communication.request;

import java.util.List;

public class AddBluetoothDataRequest extends BasicRestRequest{
    public Long userId;
    public List<SingleAddBluetoothDataRequest> bluetoothData;

    public AddBluetoothDataRequest() {
    }

    public AddBluetoothDataRequest(Long userId, List<SingleAddBluetoothDataRequest> bluetoothData) {
        this.userId = userId;
        this.bluetoothData = bluetoothData;
    }

    public AddBluetoothDataRequest(String message, Long userId, List<SingleAddBluetoothDataRequest> bluetoothData) {
        super(message);
        this.userId = userId;
        this.bluetoothData = bluetoothData;
    }

    @Override
    public String toString() {
        return "AddBluetoothDataRequest{" +
                "userId=" + userId +
                ", bluetoothData=" + bluetoothData +
                ", message='" + message + '\'' +
                '}';
    }
}
