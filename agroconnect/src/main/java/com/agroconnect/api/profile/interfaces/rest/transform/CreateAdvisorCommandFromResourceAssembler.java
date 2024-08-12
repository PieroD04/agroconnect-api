package com.agroconnect.api.profile.interfaces.rest.transform;

import com.agroconnect.api.profile.domain.model.commands.CreateAdvisorCommand;
import com.agroconnect.api.profile.interfaces.rest.resources.CreateAdvisorResource;

public class CreateAdvisorCommandFromResourceAssembler {
    public static CreateAdvisorCommand toCommandFromResource(CreateAdvisorResource resource) {
        return new CreateAdvisorCommand(
                resource.userId(),
                resource.rating());
    }
}
