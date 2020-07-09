package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19QuarantineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface C19QuarantineStatusRepository extends JpaRepository<C19QuarantineStatus, Long> {
    C19QuarantineStatus findTopByUserIdAndIsActive(Long userId, Boolean isActive);
}
