package com.agroconnect.api.publication.application.internal.queryservices;

import com.agroconnect.api.publication.domain.model.aggregates.Publication;
import com.agroconnect.api.publication.domain.model.queries.GetAllPublicationsQuery;
import com.agroconnect.api.publication.domain.model.queries.GetPublicationByAdvisorIdQuery;
import com.agroconnect.api.publication.domain.model.queries.GetPublicationByIdQuery;
import com.agroconnect.api.publication.domain.services.PublicationQueryService;
import com.agroconnect.api.publication.infrastructure.persistence.jpa.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationQueryServiceImpl implements PublicationQueryService {
    private final PublicationRepository publicationRepository;

    public PublicationQueryServiceImpl(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    @Override
    public List<Publication> handle(GetAllPublicationsQuery query) {
        return publicationRepository.findAll();
    }

    @Override
    public Optional<Publication> handle(GetPublicationByIdQuery query) {
        return publicationRepository.findById(query.id());
    }

    @Override
    public List<Publication> handle(GetPublicationByAdvisorIdQuery query) {
        return publicationRepository.findByAdvisor_Id(query.advisorId());
    }
}
