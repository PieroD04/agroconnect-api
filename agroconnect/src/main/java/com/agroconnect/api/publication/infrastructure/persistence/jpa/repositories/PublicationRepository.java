package com.agroconnect.api.publication.infrastructure.persistence.jpa.repositories;

import com.agroconnect.api.publication.domain.model.aggregates.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByAdvisor_Id(Long advisorId);
}
