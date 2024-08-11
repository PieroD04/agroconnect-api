package com.agroconnect.api.iam.interfaces.rest.transform;

import com.agroconnect.api.iam.domain.model.entities.Role;
import com.agroconnect.api.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}