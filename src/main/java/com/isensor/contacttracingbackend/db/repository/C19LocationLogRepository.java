package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19LocationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface C19LocationLogRepository extends JpaRepository<C19LocationLog, Long> {
    List<C19LocationLog> findByUserIdAndCreatedAtGreaterThanAndCreatedAtLessThan(Long userId, Long startTime, Long endTime);
}
