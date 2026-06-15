package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
public class VehicleController {
    private final VehicleService vehicleService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    ResponseEntity<List<VehicleDTO>> findAll(){
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{vehicleId}")
    ResponseEntity<VehicleDTO> findById(@PathVariable UUID vehicleId){
        return ResponseEntity.ok(vehicleService.findById(vehicleId));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    ResponseEntity<VehicleDTO> create(@Valid @RequestBody NewVehicleDTO newVehicleDTO){
        return new ResponseEntity<>(vehicleService.save(newVehicleDTO), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{vehicleId}")
    ResponseEntity<VehicleDTO> update(@PathVariable UUID vehicleId, @Valid @RequestBody NewVehicleDTO newVehicleDTO){
        return ResponseEntity.ok(vehicleService.update(vehicleId,newVehicleDTO));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{vehicleId}")
    ResponseEntity<VehicleDTO> delete(@PathVariable UUID vehicleId){
        vehicleService.delete(vehicleId);
        return ResponseEntity.noContent().build();
    }
}
