package com.isensor.contacttracingbackend.db.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "c19_treatment_log")
public class C19TreatmentLog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "test_center_id")
    public Long testCenterId;

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "consent_ids")
    public String consentIds;

    @Column(name = "report_id")
    public Long reportId;

    @Column(name = "activation_time")
    public Long activationTime;

    @Column(name = "deactivation_time")
    public Long deactivationTime;

    @Column(name = "test_type")
    public String testType;

    @Column(name = "test_result")
    public String testResult;

    @Column(name = "tracing_status")
    public String tracingStatus;

    public C19TreatmentLog() {
    }

    public C19TreatmentLog(Long id, Long testCenterId, Long userId, String consentIds, Long reportId, Long activationTime, Long deactivationTime, String testType, String testResult, String tracingStatus) {
        this.id = id;
        this.testCenterId = testCenterId;
        this.userId = userId;
        this.consentIds = consentIds;
        this.reportId = reportId;
        this.activationTime = activationTime;
        this.deactivationTime = deactivationTime;
        this.testType = testType;
        this.testResult = testResult;
        this.tracingStatus = tracingStatus;
    }

    @Override
    public String toString() {
        return "C19TreatmentLog{" +
                "id=" + id +
                ", testCenterId=" + testCenterId +
                ", userId=" + userId +
                ", consentIds='" + consentIds + '\'' +
                ", reportId=" + reportId +
                ", activationTime=" + activationTime +
                ", deactivationTime=" + deactivationTime +
                ", testType='" + testType + '\'' +
                ", testResult='" + testResult + '\'' +
                ", tracingStatus='" + tracingStatus + '\'' +
                '}';
    }
}

