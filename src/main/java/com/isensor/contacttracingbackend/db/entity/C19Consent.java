package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_consent")
public class C19Consent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "test_center_id")
    public Long testCenterId;

    @Column(name = "created_at")
    public Long createdAt;

    @Column(name = "details")
    public String details;

    @Column(name = "user_response")
    public String userResponse;

    @Column(name = "user_response_time")
    public Long userResponseTime;

    @Column(name = "is_active")
    public Boolean isActive;

    @Column(name = "title")
    public String title;

    public C19Consent() {
    }

    public C19Consent(Long id, Long userId, Long testCenterId, Long createdAt, String details, String userResponse, Long userResponseTime, Boolean isActive, String title) {
        this.id = id;
        this.userId = userId;
        this.testCenterId = testCenterId;
        this.createdAt = createdAt;
        this.details = details;
        this.userResponse = userResponse;
        this.userResponseTime = userResponseTime;
        this.isActive = isActive;
        this.title = title;
    }

    @Override
    public String toString() {
        return "C19Consent{" +
                "id=" + id +
                ", userId=" + userId +
                ", testCenterId=" + testCenterId +
                ", createdAt=" + createdAt +
                ", details='" + details + '\'' +
                ", userResponse='" + userResponse + '\'' +
                ", userResponseTime=" + userResponseTime +
                ", isActive=" + isActive +
                ", title='" + title + '\'' +
                '}';
    }
}

