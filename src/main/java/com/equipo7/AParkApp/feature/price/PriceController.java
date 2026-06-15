package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.price.domain.PriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {


    private final PriceService priceService;


    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<PriceDTO>> findAll() {

        return ResponseEntity.ok(priceService.findAll());
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> findById(@PathVariable UUID id) {

        return ResponseEntity.ok(priceService.findById(id));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PostMapping
    public ResponseEntity<PriceDTO> create(@RequestBody PriceDTO dto) {

        return ResponseEntity.ok(priceService.create(dto));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @PatchMapping("/{id}")
    public ResponseEntity<PriceDTO> update(
            @PathVariable UUID id,
            @RequestBody PriceDTO dto) {

        return ResponseEntity.ok(priceService.update(id, dto));
    }

    @PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        priceService.delete(id);

        return ResponseEntity.noContent().build();
    }

}

