package com.equipo7.AParkApp.feature.reservation;

import com.equipo7.AParkApp.feature.offer.domain.dto.AcquireOfferRequest;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationRequestDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationResponseDTO;
import com.equipo7.AParkApp.feature.reservation.domain.dto.ReservationUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Reservas", description = "Gestión de reservas y uso de ofertas")
@SecurityRequirement(name = "bearerAuth")
public class ReservationController {
    private final ReservationService reservationService;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todas las reservas")
    public List<ReservationResponseDTO> findAll() {
        return reservationService.getAll();
    }


    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene una reserva por id")
    public ReservationResponseDTO findById(@PathVariable UUID id) {
        return reservationService.getById(id);
    }


    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea una reserva")
    public ReservationResponseDTO create(@Valid @RequestBody ReservationRequestDTO request) {
        return reservationService.save(request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualiza una reserva")
    public ReservationResponseDTO modify(@PathVariable UUID id, @Valid @RequestBody ReservationUpdateRequest request) {
        return reservationService.update(id, request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/vehicle")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista reservas por patente de vehículo")
    public List<ReservationResponseDTO> findByPlate(@RequestParam String plate) {
        return reservationService.findByPlate(plate);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/check-in")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registra el check-in de una reserva")
    public ReservationResponseDTO checkIn(@PathVariable UUID id) {
        return reservationService.checkIn(id);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/check-out")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registra el check-out de una reserva")
    public ReservationResponseDTO checkOut(@PathVariable UUID id) {
        return reservationService.checkOut(id);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}/cancel")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cancela una reserva")
    public ReservationResponseDTO cancel(@PathVariable UUID id) {
        return reservationService.cancel(id);
    }
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/acquire-offer/{offerId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adquiere una oferta y crea la reserva")
    public ReservationResponseDTO acquireOffer(@PathVariable UUID offerId, @Valid @RequestBody AcquireOfferRequest request) {

        return reservationService.acquireOffer(offerId, request);
    }
}
