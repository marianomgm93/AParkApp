package com.equipo7.AParkApp.feature.ticket;

import com.equipo7.AParkApp.feature.ticket.domain.dto.PaidRequestDTO;
import com.equipo7.AParkApp.feature.ticket.domain.dto.TicketResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService service;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponseDTO> findAll() {
        return service.findAll();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/today")
    @ResponseStatus(HttpStatus.OK)
    public List<TicketResponseDTO> findToday() {
        return service.findToday();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE','CLIENT')")
    @PostMapping("/pay")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponseDTO addPay(@Valid @RequestBody PaidRequestDTO request) {
        return service.addPay(request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void cancel(@PathVariable UUID id) {
        service.delete(id);
    }
}
