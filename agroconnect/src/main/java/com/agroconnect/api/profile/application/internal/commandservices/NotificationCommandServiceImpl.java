package com.agroconnect.api.profile.application.internal.commandservices;

import com.agroconnect.api.profile.application.internal.outboundservices.acl.ExternalUserService;
import com.agroconnect.api.profile.domain.exceptions.NotificationNotFoundException;
import com.agroconnect.api.profile.domain.exceptions.UserNotFoundException;
import com.agroconnect.api.profile.domain.model.commands.CreateNotificationCommand;
import com.agroconnect.api.profile.domain.model.commands.DeleteNotificationCommand;
import com.agroconnect.api.profile.domain.model.entities.Notification;
import com.agroconnect.api.profile.domain.services.NotificationCommandService;
import com.agroconnect.api.profile.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;
    private final ExternalUserService externalUserService;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository, ExternalUserService externalUserService) {
        this.notificationRepository = notificationRepository;
        this.externalUserService = externalUserService;
    }

    @Override
    public Long handle(CreateNotificationCommand command) {
        var user = externalUserService.fetchUserById(command.userId());
        if (user.isEmpty()) {
            throw new UserNotFoundException(command.userId());
        }
        var notification = new Notification(command, user.get());
        notificationRepository.save(notification);
        return notification.getId();
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        var notification = notificationRepository.findById(command.id());
        if (notification.isEmpty()) {
            throw new NotificationNotFoundException(command.id());
        }
        notificationRepository.delete(notification.get());

    }
}
