package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicles")
@Tag(name = "Vehículos", description = "Gestión de vehículos")
@SecurityRequirement(name = "bearerAuth")
public class VehicleController {
    private final VehicleService vehicleService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "Lista todos los vehículos")
    ResponseEntity<List<VehicleDTO>> findAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{vehicleId}")
    @Operation(summary = "Obtiene un vehículo por id")
    ResponseEntity<VehicleDTO> findById(@PathVariable UUID vehicleId) {
        return ResponseEntity.ok(vehicleService.findById(vehicleId));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE','CLIENT')")
    @PostMapping
    @Operation(summary = "Crea un vehículo")
    ResponseEntity<VehicleDTO> create(@Valid @RequestBody NewVehicleDTO newVehicleDTO) {
        return new ResponseEntity<>(vehicleService.save(newVehicleDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{vehicleId}")
    @Operation(summary = "Actualiza un vehículo")
    ResponseEntity<VehicleDTO> update(@PathVariable UUID vehicleId, @Valid @RequestBody NewVehicleDTO newVehicleDTO) {
        return ResponseEntity.ok(vehicleService.update(vehicleId, newVehicleDTO));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{vehicleId}")
    @Operation(summary = "Elimina un vehículo")
    ResponseEntity<VehicleDTO> delete(@PathVariable UUID vehicleId) {
        vehicleService.delete(vehicleId);
        return ResponseEntity.noContent().build();
    }
}
