package com.equipo7.AParkApp.feature.user;

import com.equipo7.AParkApp.feature.auth.dto.NewAccountRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserRequest;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Administración de usuarios del sistema")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    @Operation(summary = "Lista todos los usuarios")
    ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un usuario por id")
    ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/email")
    @Operation(summary = "Obtiene un usuario por correo electrónico")
    ResponseEntity<UserResponse> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @Operation(summary = "Crea un usuario")
    ResponseEntity<UserResponse> create(@Valid @RequestBody NewAccountRequest user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un usuario")
    ResponseEntity<UserResponse> update(@PathVariable UUID id, @Valid @RequestBody UserRequest user) {
        return ResponseEntity.ok(userService.update(id, user));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un usuario")
    ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
