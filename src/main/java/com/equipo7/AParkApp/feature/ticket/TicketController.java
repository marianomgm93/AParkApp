package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.ticket.domain.dto.PaidRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@Tag(name = "Tickets", description = "Gestión de tickets y pagos")
@SecurityRequirement(name = "bearerAuth")
public class TicketController {
    private final TicketService service;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE', 'CLIENT', 'ADMIN')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista todos los tickets")
    public List<TicketResponseDTO> findAll(Authentication auth) {
        return service.findAll(auth);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE', 'CLIENT', 'ADMIN')")
    @GetMapping("/today")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista los tickets de hoy")
    public List<TicketResponseDTO> findToday(Authentication auth) {
        return service.findToday(auth);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Registra el pago de un ticket")
    public TicketResponseDTO addPay(@Valid @RequestBody PaidRequestDTO request, Authentication auth) {
        return service.addPay(request, auth);
    }

    @PreAuthorize("hasRole('OWNER')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Cancela un ticket")
    public void cancel(@PathVariable UUID id, Authentication auth) {
        service.delete(id, auth);
    }
}
