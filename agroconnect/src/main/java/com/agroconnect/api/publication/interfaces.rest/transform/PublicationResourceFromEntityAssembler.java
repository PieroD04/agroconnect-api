package com.agroconnect.api.publication.interfaces.rest.transform;

import com.agroconnect.api.publication.domain.model.aggregates.Publication;
import com.agroconnect.api.publication.interfaces.rest.resources.PublicationResource;

public class PublicationResourceFromEntityAssembler {
    public static PublicationResource toResourceFromEntity(Publication entity) {
        return new PublicationResource(
                entity.getId(),
                entity.getAdvisorId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getImage());
    }
}
