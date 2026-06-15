package com.equipo7.AParkApp.feature.parkingLot;

import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotRequest;
import com.equipo7.AParkApp.feature.parkingLot.Domain.DTO.ParkingLotResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ParkingLot")
public class ParkingLotController {

    private final IParkingLotService parkingLotService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ParkingLotResponse> create(@Valid @RequestBody ParkingLotRequest request) {

        ParkingLotResponse parkingLotResponse = parkingLotService.create(request);


        return ResponseEntity.status(HttpStatus.CREATED).body(parkingLotResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ParkingLotResponse>> getAll() {


        return ResponseEntity.ok(parkingLotService.getAllParkingLots());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<List<ParkingLotResponse>> getAllActiveTrue() {


        return ResponseEntity.ok(parkingLotService.getAllActiveParkingLots());
    }
    @PreAuthorize("hasRole('ADMIN')")

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLotResponse> update(@PathVariable UUID id, @RequestBody ParkingLotRequest request) {


        return ResponseEntity.ok(parkingLotService.update(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        parkingLotService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        parkingLotService.restore(id);

        return ResponseEntity.ok().build();
    }


}
