package com.agroconnect.api.appointment.domain.services;

import com.agroconnect.api.appointment.domain.model.commands.CreateAvailableDateCommand;
import com.agroconnect.api.appointment.domain.model.commands.DeleteAvailableDateCommand;
import com.agroconnect.api.appointment.domain.model.commands.UpdateAvailableDateCommand;
import com.agroconnect.api.appointment.domain.model.entities.AvailableDate;

import java.util.Optional;

public interface AvailableDateCommandService {
    Long handle(CreateAvailableDateCommand command);
    Optional<AvailableDate> handle(UpdateAvailableDateCommand command);
    void handle(DeleteAvailableDateCommand command);
}
