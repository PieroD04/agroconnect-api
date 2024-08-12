package com.agroconnect.api.profile.interfaces.rest.resources;

import java.util.Date;

public record NotificationResource(Long id,
                                   Long userId,
                                   String title,
                                   String message,
                                   Date sendAt,
                                   String meetingUrl) {
}
