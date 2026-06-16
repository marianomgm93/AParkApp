package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotClientView;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
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
@RequestMapping("/ParkingLot")
@Tag(name = "Parqueaderos", description = "Gestión de parqueaderos y vista cliente")
@SecurityRequirement(name = "bearerAuth")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Crea un parqueadero")
    public ResponseEntity<ParkingLotResponse> create(@Valid @RequestBody ParkingLotRequest request) {

        ParkingLotResponse parkingLotResponse = parkingLotService.create(request);


        return ResponseEntity.status(HttpStatus.CREATED).body(parkingLotResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Lista todos los parqueaderos")
    public ResponseEntity<List<ParkingLotResponse>> getAll() {


        return ResponseEntity.ok(parkingLotService.getAllParkingLots());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    @Operation(summary = "Lista los parqueaderos activos")
    public ResponseEntity<List<ParkingLotResponse>> getAllActiveTrue() {


        return ResponseEntity.ok(parkingLotService.getAllActiveParkingLots());
    }

    @PreAuthorize("hasRole('ADMIN')")

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un parqueadero")
    public ResponseEntity<ParkingLotResponse> update(@PathVariable UUID id, @RequestBody ParkingLotRequest request) {


        return ResponseEntity.ok(parkingLotService.update(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un parqueadero")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        parkingLotService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/restore")
    @Operation(summary = "Restaura un parqueadero eliminado")
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        parkingLotService.restore(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/client")
    @PreAuthorize("hasRole('CLIENT')")
    @Operation(summary = "Lista los parqueaderos visibles para cliente")
    public ResponseEntity<List<ParkingLotClientView>> getParkingLotsClient() {

        return ResponseEntity.ok(parkingLotService.getAllParkingLotForClient());
    }

}
