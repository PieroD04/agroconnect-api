package com.agroconnect.api.appointment.interfaces.rest.resources;

import java.time.LocalDate;

public record CreateAppointmentResource(Long advisorId,
                                        Long farmerId,
                                        String status,
                                        LocalDate scheduledDate,
                                        String startTime,
                                        String endTime) {
}
