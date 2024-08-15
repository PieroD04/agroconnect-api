package com.agroconnect.api.appointment.domain.services;

import com.agroconnect.api.appointment.domain.model.commands.CreateReviewCommand;
import com.agroconnect.api.appointment.domain.model.commands.DeleteReviewCommand;
import com.agroconnect.api.appointment.domain.model.commands.UpdateReviewCommand;
import com.agroconnect.api.appointment.domain.model.entities.Review;

import java.util.Optional;

public interface ReviewCommandService {
    Long handle(CreateReviewCommand command);
    Optional<Review> handle(UpdateReviewCommand command);
    void handle(DeleteReviewCommand command);
}
