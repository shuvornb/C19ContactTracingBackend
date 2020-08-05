package com.isensor.contacttracingbackend.controller;

import com.isensor.contacttracingbackend.communication.request.LoginRequest;
import com.isensor.contacttracingbackend.communication.request.SignupRequest;
import com.isensor.contacttracingbackend.communication.request.UpdatePasswordRequest;
import com.isensor.contacttracingbackend.communication.request.VerifyPhoneNumberRequest;
import com.isensor.contacttracingbackend.communication.response.*;
import com.isensor.contacttracingbackend.service.RegisterService;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/isensor/c19-contact-tracing/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger log = LoggerFactory.getLogger(RegisterController.class);

    @RequestMapping(value = "/phone-verification/{phoneNumber}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PhoneVerificationResponse addPhoneNumber(@PathVariable(name = "phoneNumber") String phoneNumber) {
        log.info("Phone Verification API Invoked.");
        return registerService.addAPhoneNumber(phoneNumber);
    }

    @RequestMapping(value = "/phone-verification-confirmation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse verifyPhoneNumber(@RequestBody VerifyPhoneNumberRequest request) {
        log.info("Phone Verification Confirmation API Invoked.");
        registerService.verifyPhoneNumber(request);
        return new OKResponse("Phone verification successful");
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SignupResponse signup(@RequestBody SignupRequest request) {
        log.info("Signup API Invoked.");
        return registerService.signup(request);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest request) {
        log.info("Login API Invoked.");
        return registerService.login(request);
    }

    @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FetchProfileInfoResponse fetchProfileInfo(@RequestParam(name = "token", required = false) String token, @PathVariable(value = "userId") Long userId) {
        log.info("Fetch Profile Info API Invoked.");
        jwtUtils.validateToken(token);
        return registerService.fetchProfileInfo(userId);
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public OKResponse updatePassword(@RequestParam(name = "token", required = false) String token, @RequestBody UpdatePasswordRequest request) {
        log.info("Update Password API Invoked.");
        jwtUtils.validateToken(token);
        registerService.updatePassword(request);
        return new OKResponse("Password updated successfully");
    }
}
