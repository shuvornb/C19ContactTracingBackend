package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_bluetooth_log")
public class C19BluetoothLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "scan_time")
    public Long scanTime;

    @Column(name = "device_name")
    public String deviceName;

    @Column(name = "device_type")
    public String deviceType;

    @Column(name = "bluetooth_class")
    public String bluetoothClass;

    @Column(name = "device_address")
    public String deviceAddress;

    @Column(name = "zone")
    public String zone;

    @Column(name = "nickname")
    public String nickname;

    public C19BluetoothLog() {
    }

    public C19BluetoothLog(Long id, Long userId, Long scanTime, String deviceName, String deviceType, String bluetoothClass, String deviceAddress, String zone, String nickname) {
        this.id = id;
        this.userId = userId;
        this.scanTime = scanTime;
        this.deviceName = deviceName;
        this.deviceType = deviceType;
        this.bluetoothClass = bluetoothClass;
        this.deviceAddress = deviceAddress;
        this.zone = zone;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "C19BluetoothLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", scanTime=" + scanTime +
                ", deviceName='" + deviceName + '\'' +
                ", deviceType='" + deviceType + '\'' +
                ", bluetoothClass='" + bluetoothClass + '\'' +
                ", deviceAddress='" + deviceAddress + '\'' +
                ", zone='" + zone + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

