package com.agroconnect.api.appointment.application.internal.queryservices;

import com.agroconnect.api.appointment.domain.model.entities.Review;
import com.agroconnect.api.appointment.domain.model.queries.GetAllReviewsQuery;
import com.agroconnect.api.appointment.domain.model.queries.GetReviewByAdvisorIdQuery;
import com.agroconnect.api.appointment.domain.model.queries.GetReviewByIdQuery;
import com.agroconnect.api.appointment.domain.services.ReviewQueryService;
import com.agroconnect.api.appointment.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> handle(GetReviewByIdQuery query) {
        return reviewRepository.findById(query.id());
    }

    @Override
    public Optional<Review> handle(GetReviewByAdvisorIdQuery query) {
        return reviewRepository.findByAdvisor_Id(query.advisorId());
    }
}
