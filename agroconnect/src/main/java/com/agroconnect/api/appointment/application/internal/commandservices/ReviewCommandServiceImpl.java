package com.agroconnect.api.appointment.application.internal.commandservices;

import com.agroconnect.api.appointment.domain.exceptions.AppointmentNotFoundException;
import com.agroconnect.api.appointment.domain.exceptions.InvalidRatingException;
import com.agroconnect.api.appointment.domain.exceptions.ReviewNotFoundException;
import com.agroconnect.api.appointment.domain.model.commands.CreateReviewCommand;
import com.agroconnect.api.appointment.domain.model.commands.DeleteReviewCommand;
import com.agroconnect.api.appointment.domain.model.commands.UpdateReviewCommand;
import com.agroconnect.api.appointment.domain.model.entities.Review;
import com.agroconnect.api.appointment.domain.model.queries.GetAppointmentByIdQuery;
import com.agroconnect.api.appointment.domain.services.AppointmentQueryService;
import com.agroconnect.api.appointment.domain.services.ReviewCommandService;
import com.agroconnect.api.appointment.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import com.agroconnect.api.appointment.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository, AppointmentRepository appointmentRepository) {
        this.reviewRepository = reviewRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Long handle(CreateReviewCommand command) {
        var appointment = appointmentRepository.findById(command.appointmentId());
        if (appointment.isEmpty()) throw new AppointmentNotFoundException(command.appointmentId());
        if(command.rating() < 0 || command.rating() > 5) throw new InvalidRatingException(command.rating());
        Review review = new Review(command, appointment.get());
        reviewRepository.save(review);
        return review.getId();
    }

    @Override
    public Optional<Review> handle(UpdateReviewCommand command) {
        var review = reviewRepository.findById(command.id());
        if (review.isEmpty()) return Optional.empty();
        if(command.rating() < 0 || command.rating() > 5) throw new InvalidRatingException(command.rating());
        var reviewToUpdate = review.get();
        reviewRepository.save(reviewToUpdate.update(command));
        return Optional.of(reviewToUpdate);
    }

    @Override
    public void handle(DeleteReviewCommand command) {
        var review = reviewRepository.findById(command.id());
        if (review.isEmpty()) throw new ReviewNotFoundException(command.id());
        reviewRepository.delete(review.get());
    }
}
