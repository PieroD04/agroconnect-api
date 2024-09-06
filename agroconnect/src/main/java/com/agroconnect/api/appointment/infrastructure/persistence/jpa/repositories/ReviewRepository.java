package com.agroconnect.api.appointment.infrastructure.persistence.jpa.repositories;

import com.agroconnect.api.appointment.domain.model.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByAdvisor_Id(Long advisorId);
}
