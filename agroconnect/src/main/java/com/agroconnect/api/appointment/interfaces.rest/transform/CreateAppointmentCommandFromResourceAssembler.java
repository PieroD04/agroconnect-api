package com.agroconnect.api.appointment.interfaces.rest.transform;

import com.agroconnect.api.appointment.domain.model.commands.CreateAppointmentCommand;
import com.agroconnect.api.appointment.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource){
        return new CreateAppointmentCommand(
                resource.advisorId(),
                resource.farmerId(),
                resource.status(),
                resource.scheduledDate(),
                resource.startTime(),
                resource.endTime()
        );
    }
}
