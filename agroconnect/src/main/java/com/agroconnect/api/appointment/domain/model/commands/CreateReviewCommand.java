package com.agroconnect.api.appointment.domain.model.commands;

public record CreateReviewCommand(Long appointmentId,
                                  String comment,
                                  Integer rating) {
}
