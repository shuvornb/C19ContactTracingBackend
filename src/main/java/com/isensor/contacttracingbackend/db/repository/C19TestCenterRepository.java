package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19TestCenter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface C19TestCenterRepository extends JpaRepository<C19TestCenter, Long> {
    C19TestCenter findTopByUsernameAndPassword(String username, String password);
    C19TestCenter findTopById(Long id);
}
