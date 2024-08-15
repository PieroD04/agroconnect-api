package com.agroconnect.api.appointment.interfaces.rest.resources;

public record ReviewResource(Long id,
                             Long appointmentId,
                             String comment,
                             Integer rating) {
}
