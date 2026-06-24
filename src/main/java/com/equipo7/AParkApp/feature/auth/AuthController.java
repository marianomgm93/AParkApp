package com.equipo7.AParkApp.feature.auth;

import com.equipo7.AParkApp.feature.auth.dto.AuthRequest;
import com.equipo7.AParkApp.feature.auth.dto.AuthResponse;
import com.equipo7.AParkApp.feature.auth.dto.NewAccountRequest;
import com.equipo7.AParkApp.feature.auth.jwt.JwtService;
import com.equipo7.AParkApp.feature.user.UserService;
import com.equipo7.AParkApp.feature.user.domain.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Inicio de sesión y registro de usuarios")
public class AuthController {
    private final AuthService authService;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión y genera un token JWT")
    public ResponseEntity<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest authRequest){
        UserDetails user = authService.authenticate(authRequest);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    @Operation(summary = "Registra una nueva cuenta de usuario")
    public ResponseEntity<UserResponse> registerUser(@Valid  @RequestBody NewAccountRequest newAccountRequest){
        return new ResponseEntity<>(userService.save(newAccountRequest),
                HttpStatus.CREATED);
    }
}
