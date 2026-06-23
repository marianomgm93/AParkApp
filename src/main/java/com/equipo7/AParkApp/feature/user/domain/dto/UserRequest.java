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
    @NotBlank(message = "username cannot be Blank")
    String name;

    @NotBlank
    @Email(message = "Enter a valid email")
    String email;
}
