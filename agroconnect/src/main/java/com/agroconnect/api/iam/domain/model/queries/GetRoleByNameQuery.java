package com.agroconnect.api.iam.domain.model.queries;

import com.agroconnect.api.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}