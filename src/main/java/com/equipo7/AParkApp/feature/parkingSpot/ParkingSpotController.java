package com.equipo7.AParkApp.feature.parkingSpot;

import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotRequest;
import com.equipo7.AParkApp.feature.parkingSpot.Domain.Dto.ParkingSpotResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/parkingspots")
@RequiredArgsConstructor
public class ParkingSpotController {

    private final ParkingSpotService service;


    @PostMapping
    public ResponseEntity<ParkingSpotResponse> create(@Valid @RequestBody ParkingSpotRequest request) {

        ParkingSpotResponse response = service.createParkingSpot(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotResponse>> findAll() {

        return ResponseEntity.ok(service.getAllParkingSpots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingSpotResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getParkingSpotById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingSpotResponse> update(@PathVariable UUID id, @Valid @RequestBody ParkingSpotRequest request) {

        return ResponseEntity.ok(service.updateParkingSpot(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteParkingSpotById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/restore")
    public ResponseEntity<Void> restore(@PathVariable UUID id) {
        service.restoreParkingSpot(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/occupy")
    public ResponseEntity<Void> occupy(@PathVariable UUID id) {
        service.occupy(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/release")
    public ResponseEntity<Void> release(@PathVariable UUID id) {
        service.release(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<ParkingSpotResponse>> getAvailableSpots(
            @RequestParam UUID parkingLotId,

            @RequestParam
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startTime,

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
