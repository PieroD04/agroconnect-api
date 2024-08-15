package com.agroconnect.api.appointment.domain.services;

import com.agroconnect.api.appointment.domain.model.entities.Review;
import com.agroconnect.api.appointment.domain.model.queries.GetAllReviewsQuery;
import com.agroconnect.api.appointment.domain.model.queries.GetReviewByAppointmentIdQuery;
import com.agroconnect.api.appointment.domain.model.queries.GetReviewByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    Optional<Review> handle(GetReviewByIdQuery query);
    Optional<Review> handle(GetReviewByAppointmentIdQuery query);
}
