package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.request.AddBluetoothDataRequest;
import com.isensor.contacttracingbackend.communication.request.SingleAddBluetoothDataRequest;
import com.isensor.contacttracingbackend.communication.request.UpdateNicknameRequest;
import com.isensor.contacttracingbackend.communication.response.FetchBluetoothDeviceResponse;
import com.isensor.contacttracingbackend.communication.response.FetchNewsUpdatesResponse;
import com.isensor.contacttracingbackend.db.entity.C19BluetoothLog;
import com.isensor.contacttracingbackend.db.entity.C19NewsUpdates;
import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.db.repository.C19BluetoothLogRepository;
import com.isensor.contacttracingbackend.db.repository.C19UserRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import com.isensor.contacttracingbackend.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BluetoothService {

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private C19BluetoothLogRepository bluetoothLogRepository;

    private final Logger log = LoggerFactory.getLogger(BluetoothService.class);

    @Transactional
    public void addBluetoothData(AddBluetoothDataRequest request) {
        if(request.bluetoothData.isEmpty()){
            log.error("Bluetooth data empty");
            throw new BadRequestException("Bluetooth data empty");
        }

        C19User user = userRepository.findTopById(request.userId);
        if(user == null) {
            log.error("User not found");
            throw new NotFoundException("User not found");
        }

        // batch save
        List<C19BluetoothLog> logs = new ArrayList<>();
        for(SingleAddBluetoothDataRequest entry: request.bluetoothData) {
            C19BluetoothLog log = new C19BluetoothLog();
            log.userId = user.id;
            log.deviceName = entry.deviceName;
            log.deviceType = entry.deviceType;
            log.deviceAddress = entry.deviceAddress;
            log.bluetoothClass = entry.bluetoothClass;
            log.scanTime = entry.scanTime;
            log.zone = entry.zone;
            logs.add(log);
        }
        bluetoothLogRepository.saveAll(logs);
        log.info("{} bluetooth data saved for the user: {}", request.bluetoothData.size(), request.userId);
    }

    public FetchBluetoothDeviceResponse fetchBluetoothData(Long userId, Integer count) {
        List<C19BluetoothLog> deviceList = bluetoothLogRepository.getBluetoothDevices(userId, count);
        FetchBluetoothDeviceResponse response = new FetchBluetoothDeviceResponse();
        response.message = "Fetching bluetooth data successful";
        response.deviceCount = deviceList.size();
        response.deviceList = deviceList;
        return response;
    }

    public void updateNickname(UpdateNicknameRequest request) {
        List<C19BluetoothLog> deviceList = bluetoothLogRepository.findByUserIdAndDeviceNameAndDeviceAddress(request.userId, request.deviceName, request.deviceAddress);
        for(C19BluetoothLog device: deviceList) {
            device.nickname = request.nickname;
        }
        bluetoothLogRepository.saveAll(deviceList);
    }
}
