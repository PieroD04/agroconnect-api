package com.agroconnect.api.appointment.interfaces.rest;

import com.agroconnect.api.appointment.domain.model.commands.DeleteReviewCommand;
import com.agroconnect.api.appointment.domain.model.entities.Review;
import com.agroconnect.api.appointment.domain.model.queries.GetAllReviewsQuery;
import com.agroconnect.api.appointment.domain.model.queries.GetReviewByIdQuery;
import com.agroconnect.api.appointment.domain.services.ReviewCommandService;
import com.agroconnect.api.appointment.domain.services.ReviewQueryService;
import com.agroconnect.api.appointment.interfaces.rest.resources.CreateReviewResource;
import com.agroconnect.api.appointment.interfaces.rest.resources.ReviewResource;
import com.agroconnect.api.appointment.interfaces.rest.resources.UpdateReviewResource;
import com.agroconnect.api.appointment.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import com.agroconnect.api.appointment.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import com.agroconnect.api.appointment.interfaces.rest.transform.UpdateReviewCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="api/v1/reviews", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Review Management Endpoints")
public class ReviewsController {
    private final ReviewCommandService reviewCommandService;
    private final ReviewQueryService reviewQueryService;

    public ReviewsController(ReviewCommandService reviewCommandService, ReviewQueryService reviewQueryService) {
        this.reviewCommandService = reviewCommandService;
        this.reviewQueryService = reviewQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        var getAllReviewsQuery = new GetAllReviewsQuery();
        var reviews = reviewQueryService.handle(getAllReviewsQuery);
        var reviewResources = reviews.stream().map(ReviewResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(reviewResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResource> getReviewById(@PathVariable Long id) {
        var getReviewByIdQuery = new GetReviewByIdQuery(id);
        var review = reviewQueryService.handle(getReviewByIdQuery);
        if (review.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(reviewResource);
    }

    @PostMapping
    public ResponseEntity<ReviewResource> createReview(@RequestBody CreateReviewResource createReviewResource) {
        var createReviewCommand = CreateReviewCommandFromResourceAssembler.toCommandFromResource(createReviewResource);
        Long reviewId;
        try {
            reviewId = reviewCommandService.handle(createReviewCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if (reviewId == 0L) return ResponseEntity.badRequest().build();
        var review = reviewQueryService.handle(new GetReviewByIdQuery(reviewId));
        if (review.isEmpty()) return ResponseEntity.badRequest().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return new ResponseEntity<>(reviewResource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewResource> updateReview(@PathVariable Long id, @RequestBody UpdateReviewResource updateReviewResource) {
        var updateReviewCommand = UpdateReviewCommandFromResourceAssembler.toCommandFromResource(id, updateReviewResource);
        Optional<Review> review;
        try {
            review = reviewCommandService.handle(updateReviewCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if (review.isEmpty()) return ResponseEntity.notFound().build();
        var reviewResource = ReviewResourceFromEntityAssembler.toResourceFromEntity(review.get());
        return ResponseEntity.ok(reviewResource);
    }

     @DeleteMapping("/{id}")
     public ResponseEntity<?> deleteReview(@PathVariable Long id) {
         var deleteReviewCommand = new DeleteReviewCommand(id);
         try {
             reviewCommandService.handle(deleteReviewCommand);
         } catch (Exception e) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
         }
         return ResponseEntity.ok().body("Review with id " + id + " deleted successfully");
     }
}
