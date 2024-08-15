package com.agroconnect.api.appointment.interfaces.rest.resources;

import java.time.LocalDate;

public record UpdateAvailableDateResource(LocalDate availableDate,
                                          String startTime,
                                          String endTime) {
}
