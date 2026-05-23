package com.equipo7.AParkApp.feature.vehicle;

import com.equipo7.AParkApp.feature.vehicle.domain.VehicleEntity;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.NewVehicleDTO;
import com.equipo7.AParkApp.feature.vehicle.domain.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/vehicles")
public class VehicleController {
    private final IVehicleService vehicleService;

    @GetMapping
    ResponseEntity<List<VehicleDTO>> findAll(){
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{vehicleId}")
    ResponseEntity<VehicleDTO> findById(@PathVariable UUID vehicleId){
        return ResponseEntity.ok(vehicleService.findById(vehicleId));
    }

    @PostMapping
    ResponseEntity<VehicleDTO> create(@RequestBody NewVehicleDTO newVehicleDTO){
        return new ResponseEntity<>(vehicleService.save(newVehicleDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{vehicleId}")
    ResponseEntity<VehicleDTO> updade(@PathVariable UUID vehicleId, @RequestBody NewVehicleDTO newVehicleDTO){
        return ResponseEntity.ok(vehicleService.update(vehicleId,newVehicleDTO));
    }

    @DeleteMapping("/{vehicleId}")
    ResponseEntity<VehicleDTO> delete(@PathVariable UUID vehicleId){
        vehicleService.delete(vehicleId);
        return ResponseEntity.noContent().build();
    }
}
