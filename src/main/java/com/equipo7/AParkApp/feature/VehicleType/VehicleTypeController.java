package com.equipo7.AParkApp.feature.VehicleType;


import com.equipo7.AParkApp.feature.VehicleType.domain.dto.VehicleTypeDTO;
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
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    ResponseEntity<List<VehicleTypeDTO>> findAll() {
        return ResponseEntity.ok(vehicleTypeService.findAll());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> findById(@PathVariable UUID vehicleTypeId) {
        return ResponseEntity.ok(vehicleTypeService.findById(vehicleTypeId));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    ResponseEntity<VehicleTypeDTO> create(@Valid @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return new ResponseEntity<>(vehicleTypeService.save(vehicleTypeDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> update(@PathVariable UUID vehicleTypeId, @Valid @RequestBody VehicleTypeDTO vehicleTypeDTO) {
        return ResponseEntity.ok(vehicleTypeService.update(vehicleTypeId, vehicleTypeDTO));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> delete(@PathVariable UUID vehicleTypeId) {
        vehicleTypeService.delete(vehicleTypeId);
        return ResponseEntity.noContent().build();
    }
}

