package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19Consent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface C19ConsentRepository extends JpaRepository<C19Consent, Long> {
    List<C19Consent> findByUserId(Long userId);
    C19Consent findTopById(Long id);
}
