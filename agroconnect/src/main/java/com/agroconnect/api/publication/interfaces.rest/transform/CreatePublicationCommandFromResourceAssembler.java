package com.agroconnect.api.publication.interfaces.rest.transform;

import com.agroconnect.api.publication.domain.model.commands.CreatePublicationCommand;
import com.agroconnect.api.publication.interfaces.rest.resources.CreatePublicationResource;

public class CreatePublicationCommandFromResourceAssembler {
    public static CreatePublicationCommand toCommandFromResource(CreatePublicationResource resource) {
        return new CreatePublicationCommand(
                resource.advisorId(),
                resource.title(),
                resource.description(),
                resource.image());
    }
}
