package com.agroconnect.api.publication.application.internal.outboundservices.acl;

import com.agroconnect.api.profile.domain.model.entities.Advisor;
import com.agroconnect.api.profile.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileService {
    private final ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<Advisor> fetchAdvisorById(Long advisorId) {
        return profilesContextFacade.fetchAdvisorById(advisorId);
    }
}
