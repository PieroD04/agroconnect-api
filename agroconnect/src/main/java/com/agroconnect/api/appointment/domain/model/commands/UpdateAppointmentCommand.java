package com.agroconnect.api.appointment.domain.model.commands;

import java.time.LocalDate;

public record UpdateAppointmentCommand(Long id,
                                       String status,
                                       LocalDate scheduledDate,
                                       String startTime,
                                       String endTime) {
}
