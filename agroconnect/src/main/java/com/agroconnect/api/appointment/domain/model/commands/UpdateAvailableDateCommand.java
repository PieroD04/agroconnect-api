package com.agroconnect.api.appointment.domain.model.commands;

import java.time.LocalDate;

public record UpdateAvailableDateCommand(Long id, LocalDate availableDate, String startTime, String endTime) {
}
