package com.agroconnect.api.appointment.interfaces.rest.transform;

import com.agroconnect.api.appointment.domain.model.commands.UpdateAvailableDateCommand;
import com.agroconnect.api.appointment.interfaces.rest.resources.UpdateAvailableDateResource;

public class UpdateAvailableDateCommandFromResourceAssembler {
    public static UpdateAvailableDateCommand toCommandFromResource(Long id, UpdateAvailableDateResource resource){
        return new UpdateAvailableDateCommand(
                id,
                resource.availableDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
