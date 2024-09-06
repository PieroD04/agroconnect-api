package com.agroconnect.api.appointment.interfaces.rest.transform;

import com.agroconnect.api.appointment.domain.model.commands.CreateReviewCommand;
import com.agroconnect.api.appointment.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource){
        return new CreateReviewCommand(
                resource.advisorId(),
                resource.comment(),
                resource.rating()
        );
    }
}
