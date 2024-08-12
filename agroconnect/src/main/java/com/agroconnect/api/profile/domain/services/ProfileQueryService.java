package com.agroconnect.api.profile.domain.services;

import com.agroconnect.api.profile.domain.model.aggregates.Profile;
import com.agroconnect.api.profile.domain.model.queries.GetAllProfilesQuery;
import com.agroconnect.api.profile.domain.model.queries.GetProfileByIdQuery;
import com.agroconnect.api.profile.domain.model.queries.GetProfileByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByUserIdQuery query);
}
