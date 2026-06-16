package com.equipo7.AParkApp.feature.address;

import com.equipo7.AParkApp.feature.address.domain.dto.AddressRequest;
import com.equipo7.AParkApp.feature.address.domain.dto.AddressResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Direcciones", description = "Gestión de direcciones asociadas a usuarios")
@SecurityRequirement(name = "bearerAuth")
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    @Operation(summary = "Crea una dirección")
    public ResponseEntity<AddressResponse> addAddress(@Valid @RequestBody AddressRequest request) {

        AddressResponse response = addressService.crearDireccion(request);


        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping
    @Operation(summary = "Lista todas las direcciones")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {


        return ResponseEntity.ok(addressService.obtenerTodasLasDirecciones());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una dirección por id")
    public ResponseEntity<AddressResponse> ObtenerDireccionId(@PathVariable UUID id) {

        addressService.ObtenerDireccion(id);

        return ResponseEntity.ok(addressService.ObtenerDireccion(id));

    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualiza una dirección")
    public ResponseEntity<AddressResponse> actualizarDireccion(@PathVariable UUID id, @Valid @RequestBody AddressRequest request) {


        return ResponseEntity.ok(addressService.ActualizarDireccion(id, request));

    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina una dirección")
    private ResponseEntity<Void> borrarDireccion(@PathVariable UUID id) {

        addressService.eliminarDireccion(id);

        return ResponseEntity.noContent().build();

    }


}
