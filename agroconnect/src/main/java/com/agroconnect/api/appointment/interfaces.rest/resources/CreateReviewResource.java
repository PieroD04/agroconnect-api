package com.agroconnect.api.appointment.interfaces.rest.resources;

public record CreateReviewResource(Long appointmentId,
                                   String comment,
                                   Integer rating) {
}
