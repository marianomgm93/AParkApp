package com.equipo7.AParkApp.feature.VehicleType;


import com.equipo7.AParkApp.feature.VehicleType.domain.dto.VehicleTypeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/vehicle-type")
public class VehicleTypeController {
    private final VehicleTypeService vehicleTypeService;

    @GetMapping
    ResponseEntity<List<VehicleTypeDTO>> findAll(){
        return ResponseEntity.ok(vehicleTypeService.findAll());
    }

    @GetMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> findById(@PathVariable UUID vehicleTypeId){
        return ResponseEntity.ok(vehicleTypeService.findById(vehicleTypeId));
    }

    @PostMapping
    ResponseEntity<VehicleTypeDTO> create(@Valid @RequestBody VehicleTypeDTO vehicleTypeDTO){
        return new ResponseEntity<>(vehicleTypeService.save(vehicleTypeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> update(@PathVariable UUID vehicleTypeId, @Valid @RequestBody VehicleTypeDTO vehicleTypeDTO){
        return ResponseEntity.ok(vehicleTypeService.update(vehicleTypeId, vehicleTypeDTO));
    }

    @DeleteMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> delete(@PathVariable UUID vehicleTypeId){
        vehicleTypeService.delete(vehicleTypeId);
        return ResponseEntity.noContent().build();
    }
}

