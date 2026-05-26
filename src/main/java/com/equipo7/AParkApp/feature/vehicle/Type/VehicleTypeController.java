package com.equipo7.AParkApp.feature.vehicle.Type;


import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleTypeDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleTypeDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicle-types")
public class VehicleTypeController {
    private final IVehicleTypeService vehicleTypeService;

    @GetMapping
    ResponseEntity<List<VehicleTypeDTO>> findAll(){
        return ResponseEntity.ok(vehicleTypeService.findAll());
    }

    @GetMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> findById(@PathVariable UUID vehicleTypeId){
        return ResponseEntity.ok(vehicleTypeService.findById(vehicleTypeId));
    }

    @PostMapping
    ResponseEntity<VehicleTypeDTO> create(@Valid @RequestBody NewVehicleTypeDTO newVehicleTypeDTO){
        return new ResponseEntity<>(vehicleTypeService.save(newVehicleTypeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleTypeId}")
    ResponseEntity<VehicleTypeDTO> update(@PathVariable UUID vehicleTypeId, @Valid @RequestBody NewVehicleTypeDTO newVehicleTypeDTO){
        return ResponseEntity.ok(vehicleTypeService.update(vehicleTypeId,newVehicleTypeDTO));
    }

    @DeleteMapping("/vehicleTypeId")
    ResponseEntity<VehicleTypeDTO> delete(@PathVariable UUID vehicleTypeId){
        vehicleTypeService.delete(vehicleTypeId);
        return ResponseEntity.noContent().build();
    }
}

