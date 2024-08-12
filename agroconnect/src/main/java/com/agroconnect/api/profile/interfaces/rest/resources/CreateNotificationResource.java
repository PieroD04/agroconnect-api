package com.agroconnect.api.profile.interfaces.rest.resources;

import java.util.Date;

public record CreateNotificationResource(Long userId,
                                         String title,
                                         String message,
                                         Date sendAt,
                                         String meetingUrl) {
}
