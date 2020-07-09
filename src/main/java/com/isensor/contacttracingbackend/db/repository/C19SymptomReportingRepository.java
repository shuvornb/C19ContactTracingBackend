package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19SymptomReporting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface C19SymptomReportingRepository extends JpaRepository<C19SymptomReporting, Long> {
    List<C19SymptomReporting> findByTestCenterId(Long testCenterId);
    C19SymptomReporting findTopById(Long reportId);
    List<C19SymptomReporting> findByUserIdOrderByReportingTimeDesc(Long userId);
}
