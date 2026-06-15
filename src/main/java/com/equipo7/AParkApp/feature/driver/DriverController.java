package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.feature.driver.domain.dto.DriverModifyRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")

public class DriverController {

    private final DriverService driverService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(request));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> dropDriver(@PathVariable String dni) {
        driverService.dropDriver(dni);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{dni}")
    public ResponseEntity<DriverResponse> modifyDriver(@PathVariable String dni, @RequestBody DriverModifyRequest request) {

        return ResponseEntity.ok(driverService.modifyDriver(dni, request));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping("/{dni}/vehicles/{plate}")
    public ResponseEntity<DriverResponse> addVehicleToDriver(@PathVariable String dni, @PathVariable String plate) {

        return ResponseEntity.ok(driverService.addVehicleToDriver(dni, plate));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{dni}/vehicles/{plate}")
    public ResponseEntity<DriverResponse> removeVehicleToDriver(@PathVariable String dni, @PathVariable String plate) {

        return ResponseEntity.ok(driverService.removeVehicleToDriver(dni, plate));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {

        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/active")
    public ResponseEntity<List<DriverResponse>> getAllActiveDrivers() {

        return ResponseEntity.ok(driverService.getAllActiveDrivers());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{dni}")
    public ResponseEntity<DriverResponse> getDriver(@PathVariable String dni) {

        return ResponseEntity.ok(driverService.ListDriver(dni));
    }
}
