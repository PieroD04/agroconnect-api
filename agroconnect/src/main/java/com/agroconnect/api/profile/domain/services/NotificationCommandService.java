package com.agroconnect.api.profile.domain.services;

import com.agroconnect.api.profile.domain.model.commands.CreateNotificationCommand;
import com.agroconnect.api.profile.domain.model.commands.DeleteNotificationCommand;

public interface NotificationCommandService {
    Long handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
