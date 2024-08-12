package com.agroconnect.api.profile.interfaces.rest.transform;

import com.agroconnect.api.profile.domain.model.commands.CreateNotificationCommand;
import com.agroconnect.api.profile.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.userId(),
                resource.title(),
                resource.message(),
                resource.sendAt(),
                resource.meetingUrl());
    }
}
