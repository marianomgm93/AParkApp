package com.equipo7.AParkApp.feature.VehicleType;


import com.equipo7.AParkApp.feature.VehicleType.domain.dto.VehicleTypeDTO;
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
@RequestMapping("/vehicle-type")
@Tag(name = "Tipos de vehículo", description = "Catálogo de tipos de vehículo")
@SecurityRequirement(name = "bearerAuth")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "Lista los tipos de vehículo")
    ResponseEntity<List<VehicleTypeDTO>> findAll() {
        return ResponseEntity.ok(vehicleTypeService.findAll());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{vehicleTypeId}")
    @Operation(summary = "Obtiene un tipo de vehículo por id")
    ResponseEntity<VehicleTypeDTO> findById(@PathVariable UUID vehicleTypeId) {
        return ResponseEntity.ok(vehicleTypeService.findById(vehicleTypeId));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @Operation(summary = "Crea un tipo de vehículo")
    ResponseEntity<VehicleTypeDTO> create(@Valid @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return new ResponseEntity<>(vehicleTypeService.save(vehicleTypeDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{vehicleTypeId}")
    @Operation(summary = "Actualiza un tipo de vehículo")
    ResponseEntity<VehicleTypeDTO> update(@PathVariable UUID vehicleTypeId, @Valid @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return ResponseEntity.ok(vehicleTypeService.update(vehicleTypeId, vehicleTypeDTO));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{vehicleTypeId}")
    @Operation(summary = "Elimina un tipo de vehículo")
    ResponseEntity<VehicleTypeDTO> delete(@PathVariable UUID vehicleTypeId) {
        vehicleTypeService.delete(vehicleTypeId);
        return ResponseEntity.noContent().build();
    }
}

