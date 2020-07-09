package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_symptom_reporting")
public class C19SymptomReporting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "test_center_id")
    public Long testCenterId;

    @Column(name = "symptoms")
    public String symptoms;

    @Column(name = "reporting_time")
    public Long reportingTime;

    @Column(name = "notes")
    public String notes;

    public C19SymptomReporting() {
    }

    public C19SymptomReporting(Long id, Long userId, Long testCenterId, String symptoms, Long reportingTime, String notes) {
        this.id = id;
        this.userId = userId;
        this.testCenterId = testCenterId;
        this.symptoms = symptoms;
        this.reportingTime = reportingTime;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "C19SymptomReporting{" +
                "id=" + id +
                ", userId=" + userId +
                ", testCenterId=" + testCenterId +
                ", symptoms='" + symptoms + '\'' +
                ", reportingTime=" + reportingTime +
                ", notes='" + notes + '\'' +
                '}';
    }
}

