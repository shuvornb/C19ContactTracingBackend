package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.AddBluetoothDataRequest;
import com.isensor.contacttracingbackend.communication.request.UpdateNicknameRequest;
import com.isensor.contacttracingbackend.communication.response.OKResponse;
import com.isensor.contacttracingbackend.service.BluetoothService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/isensor/c19-contact-tracing/bluetooth")
public class BluetoothController {
    @Autowired
    private BluetoothService bluetoothService;

    @Autowired
    private JWTUtils jwtUtils;

    private final Logger log = LoggerFactory.getLogger(BluetoothController.class);

    private static final int BLUETOOTH_DEVICE_DEFAULT_COUNT = 3;

    @RequestMapping(value = "/device-data", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse addBluetoothData(@RequestParam(name = "token", required = false) String token, @RequestBody AddBluetoothDataRequest request) {
        log.info("Add Bluetooth Data API Invoked.");
        jwtUtils.validateToken(token);
        bluetoothService.addBluetoothData(request);
        return new OKResponse("Bluetooth data added successfully");
    }

    @RequestMapping(value = "/device-data/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse fetchBluetoothData(@RequestParam(name = "token", required = false) String token, @RequestParam(value = "count", required = false) Integer count, @PathVariable(value = "userId") Long userId) {
        log.info("Fetch Bluetooth Data API Invoked.");
        jwtUtils.validateToken(token);
        if(count == null) count = BLUETOOTH_DEVICE_DEFAULT_COUNT;
        return new OKResponse(bluetoothService.fetchBluetoothData(userId, count));
    }

    @RequestMapping(value = "/device-data/nickname", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse updateNickname(@RequestParam(name = "token", required = false) String token, @RequestBody UpdateNicknameRequest request) {
        log.info("Update Nickname API Invoked.");
        jwtUtils.validateToken(token);
        bluetoothService.updateNickname(request);
        return new OKResponse("Nickname updated successfully");
    }
}
