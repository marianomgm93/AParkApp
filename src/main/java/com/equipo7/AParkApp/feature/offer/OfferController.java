package com.equipo7.AParkApp.feature.offer;

import com.equipo7.AParkApp.feature.offer.domain.dto.OfferRequestDTO;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;
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
@RequestMapping("/offer")
@RequiredArgsConstructor
@Tag(name = "Ofertas", description = "Gestión de ofertas promocionales")
@SecurityRequirement(name = "bearerAuth")
public class OfferController {
    private final OfferService service;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE','CLIENT')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Lista las ofertas activas")
    public List<OfferResponseDTO> findAll() {
        return service.findAllActive();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Obtiene una oferta por id")
    public OfferResponseDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crea una oferta")
    public OfferResponseDTO create(@Valid @RequestBody OfferRequestDTO request) {
        return service.save(request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualiza una oferta")
    public OfferResponseDTO update(@PathVariable UUID id, @RequestBody OfferRequestDTO request) {
        return service.update(id, request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Elimina una oferta")
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}
