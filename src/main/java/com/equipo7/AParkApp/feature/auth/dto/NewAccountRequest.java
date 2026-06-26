package com.equipo7.AParkApp.feature.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record NewAccountRequest(

        @NotBlank(message = "Name cannot be blank")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Enter a valid email")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password

) {
}