package com.equipo7.AParkApp.feature.user.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserRequest {
    @NotBlank(message = "Username cannot be blank")
    String name;

    @NotBlank(message = "The Email Mustn't Be blank")
    @Email(message = "Enter a valid email")
    String email;
}
