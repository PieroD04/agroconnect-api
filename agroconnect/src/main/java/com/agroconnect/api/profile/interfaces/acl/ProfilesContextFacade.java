package com.agroconnect.api.profile.interfaces.acl;

import com.agroconnect.api.profile.domain.model.aggregates.Profile;
import com.agroconnect.api.profile.domain.model.entities.Advisor;
import com.agroconnect.api.profile.domain.model.entities.Farmer;
import com.agroconnect.api.profile.domain.model.entities.Notification;
import com.agroconnect.api.profile.domain.model.queries.GetAdvisorByUserIdQuery;
import com.agroconnect.api.profile.domain.model.queries.GetFarmerByUserIdQuery;
import com.agroconnect.api.profile.domain.model.queries.GetNotificationsByUserIdQuery;
import com.agroconnect.api.profile.domain.model.queries.GetProfileByUserIdQuery;
import com.agroconnect.api.profile.domain.services.AdvisorQueryService;
import com.agroconnect.api.profile.domain.services.FarmerQueryService;
import com.agroconnect.api.profile.domain.services.NotificationQueryService;
import com.agroconnect.api.profile.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfilesContextFacade {
    private final ProfileQueryService profileQueryService;
    private final FarmerQueryService farmerQueryService;
    private final AdvisorQueryService advisorQueryService;
    private final NotificationQueryService notificationQueryService;

    public ProfilesContextFacade(ProfileQueryService profileQueryService, FarmerQueryService farmerQueryService, AdvisorQueryService advisorQueryService, NotificationQueryService notificationQueryService) {
        this.profileQueryService = profileQueryService;
        this.farmerQueryService = farmerQueryService;
        this.advisorQueryService = advisorQueryService;
        this.notificationQueryService = notificationQueryService;
    }

    public Optional<Profile> fetchProfileByUserId(Long userId) {
        var getProfileByUserIdQuery = new GetProfileByUserIdQuery(userId);
        return profileQueryService.handle(getProfileByUserIdQuery);
    }

    public Optional<Farmer> fetchFarmerByUserId(Long userId) {
        var getFarmerByUserIdQuery = new GetFarmerByUserIdQuery(userId);
        return farmerQueryService.handle(getFarmerByUserIdQuery);
    }

    public Optional<Advisor> fetchAdvisorByUserId(Long userId) {
        var getAdvisorByUserIdQuery = new GetAdvisorByUserIdQuery(userId);
        return advisorQueryService.handle(getAdvisorByUserIdQuery);
    }

    public List<Notification> fetchNotificationByUserId(Long userId) {
        var getNotificationsByUserIdQuery = new GetNotificationsByUserIdQuery(userId);
        return notificationQueryService.handle(getNotificationsByUserIdQuery);
    }




}
