package com.agroconnect.api.appointment.domain.model.commands;

public record CreateReviewCommand(Long advisorId,
                                  String comment,
                                  Integer rating) {
}
