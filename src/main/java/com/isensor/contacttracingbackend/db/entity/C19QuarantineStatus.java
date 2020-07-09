package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_quarantine_status")
public class C19QuarantineStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "start_time")
    public Long startTime;

    @Column(name = "end_time")
    public Long endTime;

    @Column(name = "home_location_coordinates")
    public String homeLocationCoordinates;

    @Column(name = "is_active")
    public Boolean isActive;

    public C19QuarantineStatus() {
    }

    public C19QuarantineStatus(Long id, Long userId, Long startTime, Long endTime, String homeLocationCoordinates, Boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.homeLocationCoordinates = homeLocationCoordinates;
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "C19QuarantineStatus{" +
                "id=" + id +
                ", userId=" + userId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", homeLocationCoordinates='" + homeLocationCoordinates + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}

