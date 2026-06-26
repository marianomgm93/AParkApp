package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.price.domain.PriceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Tag(name = "Precios", description = "Gestión de tarifas y precios")
@SecurityRequirement(name = "bearerAuth")
public class PriceController {


    private final PriceService priceService;


    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    @Operation(summary = "Lista todos los precios")
    public ResponseEntity<List<PriceDTO>> findAll() {

        return ResponseEntity.ok(priceService.findAll());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un precio por id")
    public ResponseEntity<PriceDTO> findById(@PathVariable UUID id) {

        return ResponseEntity.ok(priceService.findById(id));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    @Operation(summary = "Crea un precio")
    public ResponseEntity<PriceDTO> create(@RequestBody @Valid PriceDTO dto) {

        return ResponseEntity.ok(priceService.create(dto));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza un precio")
    public ResponseEntity<PriceDTO> update(
            @PathVariable UUID id,
            @RequestBody @Valid PriceDTO dto) {

        return ResponseEntity.ok(priceService.update(id, dto));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un precio")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        priceService.delete(id);

        return ResponseEntity.noContent().build();
    }

}

