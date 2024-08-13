package com.agroconnect.api.appointment.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateAppointmentResource(String status,
                                        LocalDate scheduledDate,
                                        String startTime,
                                        String endTime) {
}
