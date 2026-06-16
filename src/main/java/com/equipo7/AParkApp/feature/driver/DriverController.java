package com.equipo7.AParkApp.feature.driver;

import com.equipo7.AParkApp.feature.driver.domain.dto.DriverModifyRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverRequest;
import com.equipo7.AParkApp.feature.driver.domain.dto.DriverResponse;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/drivers")
@Tag(name = "Conductores", description = "Gestión de conductores y sus vehículos")
@SecurityRequirement(name = "bearerAuth")

public class DriverController {

    private final DriverService driverService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @Operation(summary = "Crea un conductor")
    public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody DriverRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(driverService.createDriver(request));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{dni}")
    @Operation(summary = "Elimina un conductor")
    public ResponseEntity<Void> dropDriver(@PathVariable String dni) {
        driverService.dropDriver(dni);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{dni}")
    @Operation(summary = "Actualiza un conductor")
    public ResponseEntity<DriverResponse> modifyDriver(@PathVariable String dni, @RequestBody DriverModifyRequest request) {

        return ResponseEntity.ok(driverService.modifyDriver(dni, request));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping("/{dni}/vehicles/{plate}")
    @Operation(summary = "Asocia un vehículo a un conductor")
    public ResponseEntity<DriverResponse> addVehicleToDriver(@PathVariable String dni, @PathVariable String plate) {

        return ResponseEntity.ok(driverService.addVehicleToDriver(dni, plate));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{dni}/vehicles/{plate}")
    @Operation(summary = "Desasocia un vehículo de un conductor")
    public ResponseEntity<DriverResponse> removeVehicleToDriver(@PathVariable String dni, @PathVariable String plate) {

        return ResponseEntity.ok(driverService.removeVehicleToDriver(dni, plate));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "Lista todos los conductores")
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {

        return ResponseEntity.ok(driverService.getAllDrivers());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/active")
    @Operation(summary = "Lista los conductores activos")
    public ResponseEntity<List<DriverResponse>> getAllActiveDrivers() {

        return ResponseEntity.ok(driverService.getAllActiveDrivers());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{dni}")
    @Operation(summary = "Obtiene un conductor por DNI")
    public ResponseEntity<DriverResponse> getDriver(@PathVariable String dni) {

        return ResponseEntity.ok(driverService.ListDriver(dni));
    }
}
