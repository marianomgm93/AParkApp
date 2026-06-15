package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.offer.domain.dto.AcquireOfferRequest;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationUpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponseDTO> findAll() {
        return reservationService.getAll();
    }


    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO findById(@PathVariable UUID id) {
        return reservationService.getById(id);
    }


    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO create(@Valid @RequestBody ReservationRequestDTO request) {
        return reservationService.save(request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO modify(@PathVariable UUID id, @Valid @RequestBody ReservationUpdateRequest request) {
        return reservationService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/vehicle")
    @ResponseStatus(HttpStatus.OK)
    public List<ReservationResponseDTO> findByPlate(@RequestParam String plate) {
        return reservationService.findByPlate(plate);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/check-in")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO checkIn(@PathVariable UUID id) {
        return reservationService.checkIn(id);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/check-out")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO checkOut(@PathVariable UUID id) {
        return reservationService.checkOut(id);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public ReservationResponseDTO cancel(@PathVariable UUID id) {
        return reservationService.cancel(id);
    }

    @PostMapping("/acquire-offer/{offerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponseDTO acquireOffer(@PathVariable UUID offerId, @Valid @RequestBody AcquireOfferRequest request) {

        return reservationService.acquireOffer(offerId, request);
    }
}
