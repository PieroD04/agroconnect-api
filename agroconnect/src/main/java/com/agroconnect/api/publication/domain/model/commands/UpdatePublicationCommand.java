package com.agroconnect.api.publication.domain.model.commands;

public record UpdatePublicationCommand(Long id, String title, String description, String image) {
}
