package com.agroconnect.api.appointment.interfaces.rest.transform;

import com.agroconnect.api.appointment.domain.model.entities.Review;
import com.agroconnect.api.appointment.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity){
        return new ReviewResource(
                entity.getId(),
                entity.getAppointmentId(),
                entity.getComment(),
                entity.getRating()
        );
    }
}
