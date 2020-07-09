package com.isensor.contacttracingbackend.service;

import com.isensor.contacttracingbackend.communication.request.LoginRequest;
import com.isensor.contacttracingbackend.communication.request.SignupRequest;
import com.isensor.contacttracingbackend.communication.request.VerifyPhoneNumberRequest;
import com.isensor.contacttracingbackend.communication.response.FetchProfileInfoResponse;
import com.isensor.contacttracingbackend.communication.response.LoginResponse;
import com.isensor.contacttracingbackend.communication.response.PhoneVerificationResponse;
import com.isensor.contacttracingbackend.communication.response.SignupResponse;
import com.isensor.contacttracingbackend.db.entity.C19PhoneVerification;
import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.db.repository.C19PhoneVerificationRepository;
import com.isensor.contacttracingbackend.db.repository.C19UserRepository;
import com.isensor.contacttracingbackend.exception.BadRequestException;
import com.isensor.contacttracingbackend.exception.ConflictException;
import com.isensor.contacttracingbackend.exception.NotAcceptableException;
import com.isensor.contacttracingbackend.exception.NotFoundException;
import com.isensor.contacttracingbackend.util.JWTUtils;
import org.hibernate.annotations.Fetch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired
    private C19PhoneVerificationRepository phoneVerificationRepository;

    @Autowired
    private C19UserRepository userRepository;

    @Autowired
    private JWTUtils jwtUtils;

    private Logger log = LoggerFactory.getLogger(RegisterService.class);
    private static final int VERIFICATION_CODE_EXPIRY_TIME = 5*60*1000;

    public PhoneVerificationResponse addAPhoneNumber(String phoneNumber) {
        log.info("The phone number to register is: {}", phoneNumber);
        // check if the phone number already exists
        C19PhoneVerification existingVerifiedData = phoneVerificationRepository.findByPhoneNumberAndStatus(phoneNumber, "VERIFIED");

        if(existingVerifiedData != null) {
            log.info("The phone number already exists in the system and its verified");
            throw new ConflictException("The phone number already exists in the system and its verified");
        }

        C19PhoneVerification existingNotVerifiedData = phoneVerificationRepository.findTopByPhoneNumberAndStatusOrderByCreationTimeDesc(phoneNumber, "NOT_VERIFIED");
        if(existingNotVerifiedData != null && (System.currentTimeMillis() - existingNotVerifiedData.creationTime) <= VERIFICATION_CODE_EXPIRY_TIME) {
            log.info("A verification code has been sent to this phone number and the code has not expired yet");
            throw new NotAcceptableException("A verification code has been sent to this phone number and the code has not expired yet");
        }

        // generate the verification code
        Integer verificationCode = generateRandomVerificationCode();

        // update the db
        C19PhoneVerification phoneVerification = new C19PhoneVerification();
        phoneVerification.phoneNumber = phoneNumber;
        phoneVerification.verificationCode = verificationCode;
        phoneVerification.status = "NOT_VERIFIED";
        phoneVerification.creationTime = System.currentTimeMillis();
        phoneVerification = phoneVerificationRepository.save(phoneVerification);
        log.info("New verification code has been generated for this phone number");

        // asynchronously send out the sms with verification code
        sendVerificationCodeInSMS(phoneNumber, verificationCode);

        // respond with the id
        PhoneVerificationResponse response = new PhoneVerificationResponse();
        response.id = phoneVerification.id;
        response.message = "Verification code sent";
        return response;
    }

    private void sendVerificationCodeInSMS(String phoneNumber, Integer verificationCode) {
    }

    private Integer generateRandomVerificationCode() {
        return 123456;
    }

    public void verifyPhoneNumber(VerifyPhoneNumberRequest request) {
        log.info("Phone verification request: {}", request);
        // fetch the details
        C19PhoneVerification existingData = phoneVerificationRepository.getPhoneVerificationInfoById(request.id);

        if(existingData == null) {
            log.info("This record does not exist in the database");
            throw new NotFoundException("This record does not exist in the database");
        }

        if(existingData.status.equals("VERIFIED")) {
            log.info("This phone number is already verified");
            throw new NotAcceptableException("This phone number is already verified");
        }

        // check the expiration time
        if((System.currentTimeMillis() - existingData.creationTime) > VERIFICATION_CODE_EXPIRY_TIME) {
            log.info("The verification code has already expired");
            throw new BadRequestException("The verification code has already expired");
        }

        if(!request.verificationCode.equals(existingData.verificationCode)) {
            log.info("The verification code does not match");
            throw new BadRequestException("The verification code does not match");
        }

        existingData.status = "VERIFIED";
        phoneVerificationRepository.save(existingData);
        log.info("Phone verification status updated");

    }

    public SignupResponse signup(SignupRequest request) {
        log.info("Signup request: {}", request);
        // check if this phone number or email already exists in the database or not
        C19User existingUser = userRepository.findTopByPhoneNumberOrEmail(request.phoneNumber, request.email);
        if(existingUser != null) {
            log.info("The phone number or email is already registered in the system");
            throw new ConflictException("The phone number or email is already registered in the system");
        }

        // check if the phone number is verified or not
        /*C19PhoneVerification existingVerifiedData = phoneVerificationRepository.findByPhoneNumberAndStatus(request.phoneNumber, "VERIFIED");
        if(existingVerifiedData == null) {
            log.info("The phone number is not verified in the system");
            throw new NotFoundException("The phone number is not verified in the system");
        }*/

        // create the new user object and save it
        C19User user = new C19User();
        user.email = request.email;
        user.password = request.password;
        user.phoneNumber = request.phoneNumber;
        user.firstName = request.firstName;
        user.middleName = request.middleName;
        user.lastName = request.lastName;
        user.gender = request.gender;
        user.dateOfBirth = request.dateOfBirth;
        user.addressLine1 = request.addressLine1;
        user.addressLine2 = request.addressLine2;
        user.city = request.city;
        user.county = request.county;
        user.state = request.state;
        user.zipcode = request.zipcode;
        user.country = request.country;
        user = userRepository.save(user);
        log.info("New user registered");

        SignupResponse response = new SignupResponse();
        response.userId = user.id;
        response.message = "Signup process completed";
        return response;
    }

    public LoginResponse login(LoginRequest request){
        // check if both phone number and email are null or not
        if(request.email == null && request.phoneNumber == null) {
            log.info("Email and phone number both can not null at the same time");
            throw new BadRequestException("Email and phone number both can not null at the same time");
        }

        // check if the password matches
        C19User user;
        if(request.email != null) user = userRepository.findTopByEmailAndPassword(request.email, request.password);
        else user = userRepository.findTopByPhoneNumberAndPassword(request.phoneNumber, request.password);

        if(user == null) {
            log.info("No user found with this credentials");
            throw new NotFoundException("No user found with this credentials");
        }

        // generate jwt token
        String token = jwtUtils.generateToken(user);
        log.info("Generated JWT Token: {}", token);

        // respond
        LoginResponse response = new LoginResponse();
        response.message = "Login Successful";
        response.token = token;
        response.userId = user.id;
        response.userInfo = user;

        return response;
    }

    public FetchProfileInfoResponse fetchProfileInfo(Long userId) {
        // check if user exists or not
        C19User user = userRepository.findTopById(userId);
        if(user == null) {
            log.error("User not found");
            throw new NotFoundException("User not found");
        }

        FetchProfileInfoResponse response = new FetchProfileInfoResponse();
        response.userId = user.id;
        response.email = user.email;
        response.phoneNumber = user.phoneNumber;
        response.dateOfBirth = user.dateOfBirth;
        response.gender = user.gender;
        response.fullName = user.getFullName();
        response.fullAddress = user.getFullAddress();
        return response;
    }
}
