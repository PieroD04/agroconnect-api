package com.agroconnect.api.profile.domain.services;

import com.agroconnect.api.profile.domain.model.commands.CreateFarmerCommand;
import com.agroconnect.api.profile.domain.model.commands.DeleteFarmerCommand;

import java.util.Optional;

public interface FarmerCommandService {
    Long handle(CreateFarmerCommand command);
    void handle(DeleteFarmerCommand command);
}
