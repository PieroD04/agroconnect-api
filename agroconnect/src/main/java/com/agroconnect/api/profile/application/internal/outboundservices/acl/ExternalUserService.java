package com.agroconnect.api.profile.application.internal.outboundservices.acl;

import com.agroconnect.api.iam.domain.model.aggregates.User;
import com.agroconnect.api.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalUserService {
    private final IamContextFacade userContextFacade;

    public ExternalUserService(IamContextFacade userContextFacade) {
        this.userContextFacade = userContextFacade;
    }

    public Optional<User> fetchUserById(Long userId) {
        return userContextFacade.fetchUserById(userId);
    }
}
