package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19LocationLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface C19LocationLogRepository extends JpaRepository<C19LocationLog, Long> {
}
