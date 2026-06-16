package com.equipo7.AParkApp.feature.address;

import com.equipo7.AParkApp.feature.address.domain.dto.AddressRequest;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Addresses")
@PreAuthorize("hasAnyRole('OWNER', 'EMPLOYEE', 'CLIENT')")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody AddressRequest request) {

        AddressResponse response = addressService.crearDireccion(request);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {


        return ResponseEntity.ok(addressService.obtenerTodasLasDirecciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> ObtenerDireccionId(@PathVariable UUID id) {

        addressService.ObtenerDireccion(id);

        return ResponseEntity.ok(addressService.ObtenerDireccion(id));

    }

    @PatchMapping("/{id}")
    public ResponseEntity<AddressResponse> actualizarDireccion(@PathVariable UUID id, @Valid @RequestBody AddressRequest request) {


        return ResponseEntity.ok(addressService.ActualizarDireccion(id, request));

    }


    @DeleteMapping("/{id}")
    private ResponseEntity<Void> borrarDireccion(@PathVariable UUID id) {

        addressService.eliminarDireccion(id);

        return ResponseEntity.noContent().build();

    }


}
