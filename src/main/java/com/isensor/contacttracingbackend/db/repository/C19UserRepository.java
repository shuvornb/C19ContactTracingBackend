package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface C19UserRepository extends JpaRepository<C19User, Long> {
    C19User findTopByPhoneNumberOrEmail(String phoneNumber, String email);
    C19User findTopByPhoneNumberAndPassword(String phoneNumber, String password);
    C19User findTopByEmailAndPassword(String email, String password);
    C19User findTopById(Long id);
}
