package com.agroconnect.api.profile.interfaces.rest.transform;

import com.agroconnect.api.profile.domain.model.commands.CreateFarmerCommand;
import com.agroconnect.api.profile.interfaces.rest.resources.CreateFarmerResource;

public class CreateFarmerCommandFromResourceAssembler {
    public static CreateFarmerCommand toCommandFromResource(CreateFarmerResource resource) {
        return new CreateFarmerCommand(
                resource.userId());
    }
}
