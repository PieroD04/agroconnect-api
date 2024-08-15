package com.agroconnect.api.appointment.domain.model.commands;

public record UpdateReviewCommand(Long id, String comment, Integer rating) {
}
