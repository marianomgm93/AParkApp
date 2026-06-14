package com.equipo7.AParkApp.feature.price;

import com.equipo7.AParkApp.feature.price.domain.PriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {


    private final PriceService priceService;


    @GetMapping
    public ResponseEntity<List<PriceDTO>> findAll() {

        return ResponseEntity.ok(priceService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<PriceDTO> findById(@PathVariable UUID id) {

        return ResponseEntity.ok(priceService.findById(id));
    }


    @PostMapping
    public ResponseEntity<PriceDTO> create(@RequestBody PriceDTO dto) {

        return ResponseEntity.ok(priceService.create(dto));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PriceDTO> update(
            @PathVariable UUID id,
            @RequestBody PriceDTO dto) {

        return ResponseEntity.ok(priceService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {

        priceService.delete(id);

        return ResponseEntity.noContent().build();
    }

}

