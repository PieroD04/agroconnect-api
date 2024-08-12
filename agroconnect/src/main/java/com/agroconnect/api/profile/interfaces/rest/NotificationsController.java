package com.agroconnect.api.profile.interfaces.rest;

import com.agroconnect.api.profile.domain.model.commands.DeleteNotificationCommand;
import com.agroconnect.api.profile.domain.model.queries.GetAllNotificationsQuery;
import com.agroconnect.api.profile.domain.model.queries.GetNotificationByIdQuery;
import com.agroconnect.api.profile.domain.services.NotificationCommandService;
import com.agroconnect.api.profile.domain.services.NotificationQueryService;
import com.agroconnect.api.profile.interfaces.rest.resources.CreateNotificationResource;
import com.agroconnect.api.profile.interfaces.rest.resources.NotificationResource;
import com.agroconnect.api.profile.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import com.agroconnect.api.profile.interfaces.rest.transform.NotificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@PreAuthorize("hasRole('USER')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="api/v1/notifications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notification Management Endpoints")
public class NotificationsController {
    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationsController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResource>> getAllNotifications() {
        var getAllNotificationsQuery = new GetAllNotificationsQuery();
        var notifications = notificationQueryService.handle(getAllNotificationsQuery);
        var notificationResources = notifications.stream().map(NotificationResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(notificationResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long id) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(id);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if (notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource createNotificationResource){
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(createNotificationResource);
        Long notificationId;
        try {
            notificationId = notificationCommandService.handle(createNotificationCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if(notificationId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var notification = notificationQueryService.handle(new GetNotificationByIdQuery(notificationId));
        if (notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long id) {
        var deleteNotificationCommand = new DeleteNotificationCommand(id);
        try {
            notificationCommandService.handle(deleteNotificationCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("Notification with id: " + id + " deleted successfully");
    }
}
