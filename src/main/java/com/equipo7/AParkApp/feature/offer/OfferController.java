package com.equipo7.AParkApp.feature.offer;

import com.equipo7.AParkApp.feature.offer.domain.dto.OfferRequestDTO;
import com.equipo7.AParkApp.feature.offer.domain.dto.OfferResponseDTO;
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
public class OfferController {
    private final OfferService service;

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE','CLIENT')")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OfferResponseDTO> findAll() {
        return service.findAllActive();
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OfferResponseDTO findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfferResponseDTO create(@Valid @RequestBody OfferRequestDTO request) {
        return service.save(request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OfferResponseDTO update(@PathVariable UUID id, @RequestBody OfferRequestDTO request) {
        return service.update(id, request);
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id){
        service.delete(id);
    }
}
