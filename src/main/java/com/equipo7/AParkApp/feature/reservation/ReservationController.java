package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponseDTO> findAll() {
        return reservationService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO findById(UUID id) {
        return reservationService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO create(@Valid @RequestBody ReservationRequestDTO request) {
        return reservationService.save(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO modify(@PathVariable UUID id, @Valid @RequestBody ReservationRequestDTO request) {
        return reservationService.update(id, request);
    }

    @GetMapping("/vehicle")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponseDTO> findByPlate(@RequestParam String plate) {
        return reservationService.findByPlate(plate);
    }

    @PatchMapping("/{id}/check-in")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO checkIn(@PathVariable UUID id) {
        return reservationService.checkIn(id);
    }
    @PatchMapping("/{id}/check-out")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO checkOut(@PathVariable UUID id) {
        return reservationService.checkOut(id);
    }
    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO cancel(@PathVariable UUID id) {
        return reservationService.cancel(id);
    }
}
