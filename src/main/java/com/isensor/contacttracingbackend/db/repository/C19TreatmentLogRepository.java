package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19TreatmentLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface C19TreatmentLogRepository extends JpaRepository<C19TreatmentLog, Long> {
    List<C19TreatmentLog> findByTestCenterIdAndTracingStatus(Long testCenterId, String tracingStatus);
    List<C19TreatmentLog> findByTestCenterId(Long testCenterId);
    C19TreatmentLog findTopById(long id);
    C19TreatmentLog findTopByConsentIdsContaining(String consentString);

}
