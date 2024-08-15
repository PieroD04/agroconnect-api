package com.agroconnect.api.appointment.domain.model.commands;

import java.time.LocalDate;

public record CreateAvailableDateCommand(Long advisorId,
                                         LocalDate availableDate,
                                         String startTime,
                                         String endTime) {
}
