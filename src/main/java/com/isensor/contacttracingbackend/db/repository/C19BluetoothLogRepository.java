package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19BluetoothLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface C19BluetoothLogRepository extends JpaRepository<C19BluetoothLog, Long> {
    @Query(value = "select * from c19_bluetooth_log where user_id = :userId order by scan_time desc limit :count", nativeQuery = true)
    List<C19BluetoothLog> getBluetoothDevices(Long userId, int count);
    List<C19BluetoothLog> findByUserIdAndDeviceNameAndDeviceAddress(Long userId, String deviceName, String deviceAddress);
}
