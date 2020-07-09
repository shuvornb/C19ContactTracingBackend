package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19QuarantineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface C19QuarantineStatusRepository extends JpaRepository<C19QuarantineStatus, Long> {
    List<C19QuarantineStatus> findByUserIdAndIsActive(Long userId, Boolean isActive);
}
