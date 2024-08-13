package com.agroconnect.api.appointment.domain.model.commands;

import java.time.LocalDate;

public record CreateAppointmentCommand(Long advisorId,
                                       Long farmerId,
                                       String status,
                                       LocalDate scheduledDate,
                                       String startTime,
                                       String endTime) {
}
