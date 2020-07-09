package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_phone_verification")
public class C19PhoneVerification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "phone_number")
    public String phoneNumber;

    @Column(name = "verification_code")
    public Integer verificationCode;

    @Column(name = "status")
    public String status;

    @Column(name = "creation_time")
    public Long creationTime;

    public C19PhoneVerification() {
    }

    public C19PhoneVerification(Long id, String phoneNumber, Integer verificationCode, String status, Long creationTime) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
        this.status = status;
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "C19PhoneVerification{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", verificationCode=" + verificationCode +
                ", status='" + status + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }
}

