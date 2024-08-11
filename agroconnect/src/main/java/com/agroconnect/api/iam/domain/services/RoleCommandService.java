package com.agroconnect.api.iam.domain.services;

import com.agroconnect.api.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}