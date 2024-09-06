package com.agroconnect.api.appointment.interfaces.rest.resources;

public record ReviewResource(Long id,
                             Long advisorId,
                             String comment,
                             Integer rating) {
}
