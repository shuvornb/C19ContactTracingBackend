package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_location_log")
public class C19LocationLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "created_at")
    public Long createdAt;

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "longitude")
    public Double longitude;

    public C19LocationLog() {
    }

    public C19LocationLog(Long id, Long userId, Long createdAt, Double latitude, Double longitude) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "C19LocationLog{" +
                "id=" + id +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}

