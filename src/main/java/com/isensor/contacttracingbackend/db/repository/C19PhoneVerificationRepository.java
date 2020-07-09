package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19PhoneVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface C19PhoneVerificationRepository extends JpaRepository<C19PhoneVerification, Long> {
    C19PhoneVerification findByPhoneNumberAndStatus(String phoneNumber, String status);
    C19PhoneVerification findTopByPhoneNumberAndStatusOrderByCreationTimeDesc(String phoneNumber, String status);
    @Query(value = "select * from c19_phone_verification where id =:id", nativeQuery = true)
    C19PhoneVerification getPhoneVerificationInfoById(Long id);
}
