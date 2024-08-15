package com.agroconnect.api.appointment.interfaces.rest.transform;

import com.agroconnect.api.appointment.domain.model.commands.UpdateReviewCommand;
import com.agroconnect.api.appointment.interfaces.rest.resources.UpdateReviewResource;

public class UpdateReviewCommandFromResourceAssembler {
    public static UpdateReviewCommand toCommandFromResource(Long id, UpdateReviewResource resource){
        return new UpdateReviewCommand(
                id,
                resource.comment(),
                resource.rating()
        );
    }
}
