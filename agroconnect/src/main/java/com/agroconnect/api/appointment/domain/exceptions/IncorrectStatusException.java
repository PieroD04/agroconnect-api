package com.agroconnect.api.appointment.domain.exceptions;

public class IncorrectStatusException extends RuntimeException {
    public IncorrectStatusException(String status) {
        super("Incorrect status: " + status + ". Status should be either 'PENDING', 'ONGOING', 'COMPLETED' or 'REVIEWED'");
    }
}
