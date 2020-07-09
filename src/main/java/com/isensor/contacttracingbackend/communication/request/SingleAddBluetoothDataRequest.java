package com.isensor.contacttracingbackend.communication.request;

public class SingleAddBluetoothDataRequest {
    public String deviceName;
    public String deviceType;
    public String deviceAddress;
    public String bluetoothClass;
    public Long scanTime;
    public String zone;

    public SingleAddBluetoothDataRequest() {
    }

    public SingleAddBluetoothDataRequest(String deviceName, String deviceType, String deviceAddress, String bluetoothClass, Long scanTime, String zone) {
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.deviceAddress = deviceAddress;
        this.bluetoothClass = bluetoothClass;
        this.scanTime = scanTime;
        this.zone = zone;
    }

    @Override
    public String toString() {
        return "SingleAddBluetoothDataRequest{" +
                "deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", bluetoothClass='" + bluetoothClass + '\'' +
                ", scanTime=" + scanTime +
                ", zone='" + zone + '\'' +
                '}';
    }
}
