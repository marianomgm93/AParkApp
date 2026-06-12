package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.feature.driver.domain.dto.DriverModifyRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")

public class DriverController {

    private final DriverService driverService;


    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(request));
    }


    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> dropDriver(@PathVariable String dni) {
        driverService.dropDriver(dni);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{dni}")
    public ResponseEntity<DriverResponse> modifyDriver(@PathVariable String dni, @RequestBody DriverModifyRequest request) {

        return ResponseEntity.ok(driverService.modifyDriver(dni, request));
    }


    @PostMapping("/{dni}/vehicles/{plate}")
    public ResponseEntity<DriverResponse> addVehicleToDriver(@PathVariable String dni, @PathVariable String plate) {

        return ResponseEntity.ok(driverService.addVehicleToDriver(dni, plate));
    }


    @DeleteMapping("/{dni}/vehicles/{plate}")
    public ResponseEntity<DriverResponse> removeVehicleToDriver(@PathVariable String dni, @PathVariable String plate) {

        return ResponseEntity.ok(driverService.removeVehicleToDriver(dni, plate));
    }


    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {

        return ResponseEntity.ok(driverService.getAllDrivers());
    }


    @GetMapping("/active")
    public ResponseEntity<List<DriverResponse>> getAllActiveDrivers() {

        return ResponseEntity.ok(driverService.getAllActiveDrivers());
    }


    @GetMapping("/{dni}")
    public ResponseEntity<DriverResponse> getDriver(@PathVariable String dni) {

        return ResponseEntity.ok(driverService.ListDriver(dni));
    }
}
