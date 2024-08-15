package com.agroconnect.api.appointment.interfaces.rest.transform;

import com.agroconnect.api.appointment.domain.model.commands.CreateAvailableDateCommand;
import com.agroconnect.api.appointment.interfaces.rest.resources.CreateAvailableDateResource;

public class CreateAvailableDateCommandFromResourceAssembler {
    public static CreateAvailableDateCommand toCommandFromResource(CreateAvailableDateResource resource){
        return new CreateAvailableDateCommand(
                resource.advisorId(),
                resource.availableDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
