package com.agroconnect.api.publication.interfaces.rest.resources;

public record UpdatePublicationResource(Long advisorId, String title, String description, String image) {
}
