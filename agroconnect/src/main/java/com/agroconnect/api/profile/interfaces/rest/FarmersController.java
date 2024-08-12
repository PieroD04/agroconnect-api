package com.agroconnect.api.profile.interfaces.rest;

import com.agroconnect.api.profile.domain.model.commands.DeleteFarmerCommand;
import com.agroconnect.api.profile.domain.model.queries.GetAllFarmersQuery;
import com.agroconnect.api.profile.domain.model.queries.GetFarmerByIdQuery;
import com.agroconnect.api.profile.domain.services.FarmerCommandService;
import com.agroconnect.api.profile.domain.services.FarmerQueryService;
import com.agroconnect.api.profile.interfaces.rest.resources.CreateFarmerResource;
import com.agroconnect.api.profile.interfaces.rest.resources.FarmerResource;
import com.agroconnect.api.profile.interfaces.rest.transform.CreateFarmerCommandFromResourceAssembler;
import com.agroconnect.api.profile.interfaces.rest.transform.FarmerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="api/v1/farmers", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Farmers", description = "Farmer Management Endpoints")
public class FarmersController {
    private final FarmerCommandService farmerCommandService;
    private final FarmerQueryService farmerQueryService;

    public FarmersController(FarmerCommandService farmerCommandService, FarmerQueryService farmerQueryService) {
        this.farmerCommandService = farmerCommandService;
        this.farmerQueryService = farmerQueryService;
    }

    @GetMapping
    public ResponseEntity<List<FarmerResource>> getAllFarmers() {
        var getAllFarmersQuery = new GetAllFarmersQuery();
        var farmers = farmerQueryService.handle(getAllFarmersQuery);
        var farmerResources = farmers.stream().map(FarmerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(farmerResources);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmerResource> getFarmerById(@PathVariable Long id) {
        var getFarmerByIdQuery = new GetFarmerByIdQuery(id);
        var farmer = farmerQueryService.handle(getFarmerByIdQuery);
        if (farmer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var farmerResource = FarmerResourceFromEntityAssembler.toResourceFromEntity(farmer.get());
        return ResponseEntity.ok(farmerResource);
    }

    @PostMapping
    public ResponseEntity<FarmerResource> createFarmer(@RequestBody CreateFarmerResource createFarmerResource) {
        var createFarmerCommand = CreateFarmerCommandFromResourceAssembler.toCommandFromResource(createFarmerResource);
        Long farmerId;
        try {
            farmerId = farmerCommandService.handle(createFarmerCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
        }
        if(farmerId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var farmer = farmerQueryService.handle(new GetFarmerByIdQuery(farmerId));
        if (farmer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var farmerResource = FarmerResourceFromEntityAssembler.toResourceFromEntity(farmer.get());
        return new ResponseEntity<>(farmerResource, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFarmer(@PathVariable Long id) {
        var deleteFarmerCommand = new DeleteFarmerCommand(id);
        try {
            farmerCommandService.handle(deleteFarmerCommand);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
        }
        return ResponseEntity.ok().body("Farmer with id " + id + " deleted successfully");
    }
}
