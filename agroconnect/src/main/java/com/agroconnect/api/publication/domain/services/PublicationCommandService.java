package com.agroconnect.api.publication.domain.services;

import com.agroconnect.api.publication.domain.model.aggregates.Publication;
import com.agroconnect.api.publication.domain.model.commands.CreatePublicationCommand;
import com.agroconnect.api.publication.domain.model.commands.DeletePublicationCommand;
import com.agroconnect.api.publication.domain.model.commands.UpdatePublicationCommand;

import java.util.Optional;

public interface PublicationCommandService {
    Long handle(CreatePublicationCommand command);
    Optional<Publication> handle(UpdatePublicationCommand command);
    void handle(DeletePublicationCommand command);
}
