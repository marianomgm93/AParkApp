package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parkingspots")
@RequiredArgsConstructor
@Tag(name = "Celdas de parqueo", description = "Gestión de espacios de estacionamiento")
@SecurityRequirement(name = "bearerAuth")
public class ParkingSpotController {

    private final ParkingSpotService service;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @Operation(summary = "Crea una celda de parqueo")
    public ResponseEntity<ParkingSpotResponse> create(@Valid @RequestBody ParkingSpotRequest request) {

        ParkingSpotResponse response = service.createParkingSpot(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "Lista todas las celdas de parqueo")
    public ResponseEntity<List<ParkingSpotResponse>> findAll() {

        return ResponseEntity.ok(service.getAllParkingSpots());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una celda de parqueo por id")
    public ResponseEntity<ParkingSpotResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getParkingSpotById(id));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza una celda de parqueo")
    public ResponseEntity<ParkingSpotResponse> update(@PathVariable UUID id, @Valid @RequestBody ParkingSpotRequest request) {

        return ResponseEntity.ok(service.updateParkingSpot(id, request));
    }
    @Operation(summary = "Elimina una celda de parqueo")

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteParkingSpotById(id);
        return ResponseEntity.noContent().build();
    }
    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/restore")
    @Operation(summary = "Restaura una celda de parqueo")
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        service.restoreParkingSpot(id);
        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/occupy")
    @Operation(summary = "Marca una celda como ocupada")
    public ResponseEntity<Void> occupy(@PathVariable UUID id) {
        service.occupy(id);

        return ResponseEntity.ok().build();
    }
    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/release")
    @Operation(summary = "Libera una celda ocupada")
    public ResponseEntity<Void> release(@PathVariable UUID id) {
        service.release(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/available")
        @Operation(summary = "Consulta celdas disponibles en un rango horario")
    public ResponseEntity<List<ParkingSpotResponse>> getAvailableSpots(
            @Parameter(description = "ID del parqueadero")
            @RequestParam UUID parkingLotId,

            @Parameter(description = "Fecha y hora de inicio")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startTime,

            @Parameter(description = "Fecha y hora de fin")
            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime endTime) {

        return ResponseEntity.ok(
                service.findAvailableSpots(
                        parkingLotId,
                        startTime,
                        endTime
                )
        );
    }


}
